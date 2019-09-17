package dev.rodni.ru.cache

import dev.rodni.ru.cache.db.AppDatabase
import dev.rodni.ru.cache.mapper.CachedProjectMapper
import dev.rodni.ru.cache.model.Config
import dev.rodni.ru.data.model.ProjectEntity
import dev.rodni.ru.data.repository.ProjectsCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * this class is the implementation of the
 * @see ProjectsCache interface in the data layer's module
 *
 * the constructor takes two params:
 * 1. app database to make all caching operations
 * 2. the cache project mapper to send and receive objects in and out with project entity in the data layer
 */
class ProjectsCacheImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val mapper: CachedProjectMapper
): ProjectsCache {

    /**
     * delete all the bookmarked projects from the app database
     */
    override fun clearProjects(projects: List<ProjectEntity>): Completable {
        return Completable.defer{
            appDatabase.cachedProjectsDao().deleteProjects(
                projects.map {mapper.mapToCached(it) })
            Completable.complete()
        }
    }

    /**
     * making save to the db operation
     */
    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return Completable.defer{
            appDatabase.cachedProjectsDao().insertProjects(
                projects.map { mapper.mapToCached(it) })
            Completable.complete()
        }
    }

    /**
     * gets project's list from the db
     */
    override fun getProjects(): Flowable<List<ProjectEntity>> {
        return appDatabase.cachedProjectsDao().getProjects()
            //.toFlowable()
            .map {
                it.map {
                    mapper.mapFromCached(it)
                }
            }
    }

    /**
     * gets bookmarked project's list from the db
     */
    override fun getBookmarkedProjects(): Flowable<List<ProjectEntity>> {
        return appDatabase.cachedProjectsDao().getBookmarkedProjects()
            //.toObservable()
            .map {
                it.map {
                    mapper.mapFromCached(it)
                }
            }
    }

    /**
     * setting a project status to be bookmarked by its given id
     */
    override fun setProjectAsBookmarked(projectId: String): Completable {
        return Completable.defer{
            appDatabase.cachedProjectsDao().setBookmarkStatus(true, projectId)
            Completable.complete()
        }
    }

    /**
     * setting a project status to be UNbookmarked by its given id
     */
    override fun setProjectNotBookmarked(projectId: String): Completable {
        return Completable.defer{
            appDatabase.cachedProjectsDao().setBookmarkStatus(false, projectId)
            Completable.complete()
        }
    }

    /**
     * checks if a user has some cached projects
     */
    override fun areProjectsCached(): Single<Boolean> {
        return appDatabase.cachedProjectsDao().getProjects().isEmpty
            .map { !it }
    }

    /**
     * sets the last cached time to the db
     * using the config dao here not the cached projects dao as in the previous functions
     */
    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer{
            appDatabase.confidDao().insertConfig(Config(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    /**
     * creating val which takes current time
     * and then check if not too much time passed already
     *
     * returns true if already passed
     * return false if not passed yer
     */
    override fun isProjectsCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60*10*1000).toLong()
        return appDatabase.confidDao().getConfig()
            .single(Config(lastCacheTime = 0))
            .map {
                currentTime - it.lastCacheTime > expirationTime
            }
    }
}