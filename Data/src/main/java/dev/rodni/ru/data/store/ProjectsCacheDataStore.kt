package dev.rodni.ru.data.store

import dev.rodni.ru.data.model.ProjectEntity
import dev.rodni.ru.data.repository.ProjectsCache
import dev.rodni.ru.data.repository.ProjectsDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * to discover implemented methods go to the parent here
 * @see ProjectsDataStore interface
 * this class is open because of the testing
 */
open class ProjectsCacheDataStore @Inject constructor(
    private val projectsCache: ProjectsCache
) : ProjectsDataStore {

    override fun getProjects(): Flowable<List<ProjectEntity>> {
        return projectsCache.getProjects()
    }

    //also im setting up the last time of using save into cache operation
    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return projectsCache.saveProjects(projects)
            .andThen(projectsCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun clearProjects(projects: List<ProjectEntity>): Completable {
        return projectsCache.clearProjects(projects)
    }

    override fun getBookmarkedProjects(): Flowable<List<ProjectEntity>> {
        return projectsCache.getBookmarkedProjects()
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return projectsCache.setProjectAsBookmarked(projectId)
    }

    override fun setProjectNotBookmarked(projectId: String): Completable {
        return projectsCache.setProjectNotBookmarked(projectId)
    }
}