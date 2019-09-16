package dev.rodni.ru.domain.interactor.bookmark

import com.nhaarman.mockito_kotlin.whenever
import dev.rodni.ru.domain.executor.PostExecutionThread
import dev.rodni.ru.domain.interactor.bookmarked.GetBookmarkedProjects
import dev.rodni.ru.domain.model.Project
import dev.rodni.ru.domain.repository.TrendingProjectsRepository
import dev.rodni.ru.domain.test.ProjectDataFactory
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

//test for the GetBookmarkProjects use case
class GetBookmarkProjectsTest {

    private lateinit var getBookmarkedProjects: GetBookmarkedProjects
    @Mock lateinit var trendingProjectsRepository: TrendingProjectsRepository
    @Mock lateinit var executionThread: PostExecutionThread

    //setting up the constructor's dependencies
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBookmarkedProjects = GetBookmarkedProjects(trendingProjectsRepository, executionThread)
    }

    @Test
    fun getBookmarkedProjectsComplete() {
        stubGetProjects(Observable.just(ProjectDataFactory.makeProjectList(3)))
        val testObserver = getBookmarkedProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        val projects = ProjectDataFactory.makeProjectList(3)
        stubGetProjects(Observable.just(projects))
        val testObserver = getBookmarkedProjects.buildUseCaseObservable().test()
        testObserver.assertValue(projects)
    }

    //checking that calling to the repo returns an observable
    private fun stubGetProjects(observable: Observable<List<Project>>) {
        whenever(trendingProjectsRepository.getBookmarkedProjects())
            .thenReturn(observable)
    }
}