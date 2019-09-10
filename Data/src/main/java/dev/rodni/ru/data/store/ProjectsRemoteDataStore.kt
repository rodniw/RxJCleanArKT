package dev.rodni.ru.data.store

import dev.rodni.ru.data.model.ProjectEntity
import dev.rodni.ru.data.repository.ProjectsDataStore
import dev.rodni.ru.data.repository.ProjectsRemote
import io.reactivex.Completable
import io.reactivex.Observable
import java.lang.UnsupportedOperationException
import javax.inject.Inject

/**
 * this class support only fetching projects method
 * if uses this class other methods is not supported
 *
 * list of all the method and what they do
 * @see ProjectsDataStore interface
 *
 * this class is open because of the testing
 */
open class ProjectsRemoteDataStore @Inject constructor(
    private val projectsRemote: ProjectsRemote
) : ProjectsDataStore{

    //the only method that i implement here
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsRemote.getProjects()
    }

    //---NOT implemented and will not be in this class
    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException("this method isn t supported here")
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("this method isn t supported here")
    }

    override fun setProjectNotBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("this method isn t supported here")
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException("this method isn t supported here")
    }

    override fun clearProjects(): Completable {
        throw UnsupportedOperationException("this method isn t supported here")
    }
}