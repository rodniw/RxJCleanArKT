package dev.rodni.ru.data

import dev.rodni.ru.data.mapper.ProjectMapper
import dev.rodni.ru.data.repository.ProjectsCache
import dev.rodni.ru.data.store.ProjectsDataStoreFactory
import dev.rodni.ru.domain.model.Project
import dev.rodni.ru.domain.repository.TrendingProjectsRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * this repository class is the bridge between the domain layer and the data layer
 *
 * the constructor takes three parameters:
 * 1. layer's entity mapper
 * 2. projects cache repo for those request when i know exactly that i will use only cached data store
 * 3. factory which makes a decision which data store to use in which case
 */
open class TrendingProjectsDataRepository @Inject constructor(
    private val mapper: ProjectMapper,
    private val cache: ProjectsCache,
    private val factory: ProjectsDataStoreFactory
): TrendingProjectsRepository{

    /**
     * this method fetch projects and cache them only if they are not cached and cache is not too old yet
     * if a user already has cached and not expired projects then this method will fetch data
     * from cache data store
     */
    override fun getProjects(): Flowable<List<Project>> {
        return Flowable
            .zip<Boolean, Boolean, Pair<Boolean, Boolean>>(cache.areProjectsCached().toFlowable(),
            cache.isProjectsCacheExpired().toFlowable(),
            BiFunction { areCached, isExpired -> Pair(areCached, isExpired) })
                //return list of project entity from project data store
            .flatMap {
                factory.getDataStore(it.first, it.second).getProjects()
            }
                //at a time of the very first fetching already save projects to a cache
            .flatMap {
                factory.getCacheDataStore()
                    .saveProjects(it)
                    .andThen(Flowable.just(it))
            }
                //this fetches an object by projects mapper
            .map {
                it.map {
                    mapper.mapFromEntity(it)
                }
            }

    }

    /**
     * this method bookmark a project with a given project id
     */
    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsBookmarked(projectId)
    }

    /**
     * this method unbookmark a project with a given project id
     */
    override fun unbookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectNotBookmarked(projectId)
    }

    /**
     * this method fetches list of bookmarked projects and transform items into data's layer entities
     */
    override fun getBookmarkedProjects(): Flowable<List<Project>> {
        return factory.getCacheDataStore().getBookmarkedProjects()
            .map {
                it.map { mapper.mapFromEntity(it)}
            }
    }
}