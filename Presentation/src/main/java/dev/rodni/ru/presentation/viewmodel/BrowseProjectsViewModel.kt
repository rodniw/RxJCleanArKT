package dev.rodni.ru.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.rodni.ru.domain.interactor.browse.GetProjects
import dev.rodni.ru.presentation.mapper.ProjectViewMapper
import dev.rodni.ru.presentation.model.ProjectView
import dev.rodni.ru.presentation.state.Resource
import javax.inject.Inject

/**
 * view model for the list of trending repos
 */
class BrowseProjectsViewModel @Inject constructor(
    private val getProjects: GetProjects,
    private val mapper: ProjectViewMapper
): ViewModel() {

    /**
     * live data Data sender to a view
     */
    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()


}