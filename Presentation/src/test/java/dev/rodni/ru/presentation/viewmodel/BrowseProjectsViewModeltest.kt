package dev.rodni.ru.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.*
import dev.rodni.ru.domain.interactor.bookmarked.BookmarkProject
import dev.rodni.ru.domain.interactor.bookmarked.UnbookmarkProject
import dev.rodni.ru.domain.interactor.browse.GetProjects
import dev.rodni.ru.domain.model.Project
import dev.rodni.ru.presentation.factory.DataFactory
import dev.rodni.ru.presentation.factory.ProjectFactory
import dev.rodni.ru.presentation.mapper.ProjectViewMapper
import dev.rodni.ru.presentation.model.ProjectView
import dev.rodni.ru.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor

/**
 * class for tests
 * @see BrowseProjectsViewModel
 */
@RunWith(JUnit4::class)
class BrowseProjectsViewModeltest {

    /**
     * special rule for testing
     */
    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    var getProjects = mock<GetProjects>()
    var bookmarkProject = mock<BookmarkProject>()
    var unbookmarkProject = mock<UnbookmarkProject>()
    var projectMapper = mock<ProjectViewMapper>()
    var projectViewModel = BrowseProjectsViewModel(getProjects, bookmarkProject, unbookmarkProject, projectMapper)

    @Captor
    val captor = argumentCaptor<DisposableObserver<List<Project>>>()

    @Test
    fun fetchProjectsShouldExecuteUseCase() {
        projectViewModel.fetchProjects()

        verify(getProjects, times(1)).execute(any(), eq(null))
    }

    /**
     * if projects are fetched
     * then in should be SUCCESS ResourceState
     */
    @Test
    fun fetchProjectsShouldReturnSuccess() {
        val projects = ProjectFactory.makeProjectList(3)
        val projectViews = ProjectFactory.makeProjectViewList(3)
        stubProjectMapperShouldMapToView(projectViews[0], projects[0])
        stubProjectMapperShouldMapToView(projectViews[1], projects[1])
        stubProjectMapperShouldMapToView(projectViews[2], projects[2])

        projectViewModel.fetchProjects()

        verify(getProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(projects)

        assertEquals(ResourceState.SUCCESS,
            projectViewModel.getProjects().value?.status)
    }

    /**
     * if projects are fetched
     * then they have to return some data
     */
    @Test
    fun fetchProjectsShouldReturnData() {
        val projects = ProjectFactory.makeProjectList(3)
        val projectViews = ProjectFactory.makeProjectViewList(3)
        stubProjectMapperShouldMapToView(projectViews[0], projects[0])
        stubProjectMapperShouldMapToView(projectViews[1], projects[1])
        stubProjectMapperShouldMapToView(projectViews[2], projects[2])

        projectViewModel.fetchProjects()

        verify(getProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(projects)

        assertEquals(projectViews,
            projectViewModel.getProjects().value?.data)
    }

    /**
     * checking for ERROR case
     */
    @Test
    fun fetchProjectsShouldReturnError() {
        projectViewModel.fetchProjects()

        verify(getProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assertEquals(ResourceState.ERROR,
            projectViewModel.getProjects().value?.status)
    }

    /**
     * checking for ERROR case sends an error message
     */
    @Test
    fun fetchProjectsShouldReturnMessageForError() {
        val errorMessage = DataFactory.randomString()
        projectViewModel.fetchProjects()

        verify(getProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))

        assertEquals(errorMessage,
            projectViewModel.getProjects().value?.message)
    }

    /**
     * mapping pojo
     */
    private fun stubProjectMapperShouldMapToView(projectView: ProjectView, project: Project ) {
        whenever(projectMapper.mapToView(project))
            .thenReturn(projectView)
    }
}