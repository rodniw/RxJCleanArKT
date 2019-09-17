package dev.rodni.ru.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.rodni.ru.domain.interactor.bookmarked.BookmarkProject
import dev.rodni.ru.domain.interactor.bookmarked.UnbookmarkProject
import dev.rodni.ru.domain.interactor.browse.GetProjects
import dev.rodni.ru.domain.model.Project
import dev.rodni.ru.presentation.mapper.ProjectViewMapper
import dev.rodni.ru.presentation.model.ProjectView
import dev.rodni.ru.presentation.state.Resource
import dev.rodni.ru.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * view model for the list of trending repos
 */
open class BrowseProjectsViewModel @Inject internal constructor(
    private val getProjects: GetProjects?,
    private val bookmarkProject: BookmarkProject,
    private val unBookmarkProject: UnbookmarkProject,
    private val mapper: ProjectViewMapper): ViewModel() {

    /**
     * view model's live data
     */
    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    init {
        fetchProjects()
    }

    /**
     * this method clears all the disposables
     */
    override fun onCleared() {
        getProjects?.dispose()
        super.onCleared()
    }

    /**
     * getter fot the live data object
     */
    fun getProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    /**
     * fetching projects with
     * @see ProjectsSubscriber
     */
    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getProjects?.execute(ProjectsSubscriber())
    }

    /**
     * bookmark operation with the help of
     * @see BookmarkProjectsSubscriber
     */
    fun bookmarkProject(projectId: String) {
        return bookmarkProject.execute(BookmarkProjectsSubscriber(),
            BookmarkProject.Params.forProject(projectId))
    }

    /**
     * unbookmark operation with the help of
     * @see BookmarkProjectsSubscriber
     */
    fun unbookmarkProject(projectId: String) {
        return unBookmarkProject.execute(BookmarkProjectsSubscriber(),
            UnbookmarkProject.Params.forProject(projectId))
    }

    /**
     * inner class helper for the method
     * @see fetchProjects
     */
    inner class ProjectsSubscriber: DisposableObserver<List<Project>>() {
        override fun onNext(t: List<Project>) {
            liveData.postValue(Resource(ResourceState.SUCCESS,
                t.map { mapper.mapToView(it) }, null))
        }

        override fun onComplete() { }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }

    /**
     * inner class helper for the methods
     * @see bookmarkProject
     * @see unbookmarkProject
     */
    inner class BookmarkProjectsSubscriber: DisposableCompletableObserver() {
        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, liveData.value?.data,
                e.localizedMessage))
        }

    }
}