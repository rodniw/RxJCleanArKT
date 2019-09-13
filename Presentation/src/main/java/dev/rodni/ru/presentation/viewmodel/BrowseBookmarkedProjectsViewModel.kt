package dev.rodni.ru.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.rodni.ru.domain.interactor.bookmarked.GetBookmarkedProjects
import dev.rodni.ru.domain.model.Project
import dev.rodni.ru.presentation.mapper.ProjectViewMapper
import dev.rodni.ru.presentation.model.ProjectView
import dev.rodni.ru.presentation.state.Resource
import dev.rodni.ru.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * view model for the list of saved projects
 */
class BrowseBookmarkedProjectsViewModel @Inject constructor(
    private val getBookmarkedProjects: GetBookmarkedProjects?,
    private val mapper: ProjectViewMapper
): ViewModel() {

    /**
     * view model's live data
     */
    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    //init {
        //fetchProjects()
    //}

    /**
     * this method clears all the disposables
     */
    override fun onCleared() {
        getBookmarkedProjects?.dispose()
        super.onCleared()
    }

    /**
     * getter fot the live data object
     */
    fun getBookmarkedProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    /**
     * fetching projects with the help of
     * @see ProjectsSubscriber
     */
    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getBookmarkedProjects?.execute(ProjectsSubscriber())
    }

    /**
     * helper class for the
     * @see fetchProjects
     */
    inner class ProjectsSubscriber: DisposableObserver<List<Project>>() {
        override fun onComplete() {}

        override fun onNext(t: List<Project>) {
            liveData.postValue(Resource(ResourceState.SUCCESS,
                t.map { mapper.mapToView(it) }, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null,
                e.localizedMessage))
        }
    }
}