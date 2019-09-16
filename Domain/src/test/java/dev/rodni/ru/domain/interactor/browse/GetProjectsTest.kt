package dev.rodni.ru.domain.interactor.browse

import com.nhaarman.mockito_kotlin.whenever
import dev.rodni.ru.domain.executor.PostExecutionThread
import dev.rodni.ru.domain.model.Project
import dev.rodni.ru.domain.repository.TrendingProjectsRepository
import dev.rodni.ru.domain.test.ProjectDataFactory
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetProjectsTest {

    private lateinit var getProjects: GetProjects
    //mocking constr parameters
    @Mock lateinit var trendingProjectsRepository: TrendingProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        //setting up constructor's dependencies
        MockitoAnnotations.initMocks(this)
        getProjects = GetProjects(trendingProjectsRepository, postExecutionThread)
    }

    @Test
    fun getProjectsComplete() {
        stubGetProjects(Observable.just(ProjectDataFactory.makeProjectList(3)))
        val testObserver = getProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val projects = ProjectDataFactory.makeProjectList(3)
        stubGetProjects(Observable.just(projects))
        val testObserver = getProjects.buildUseCaseObservable().test()
        testObserver.assertValue(projects)
    }

    //checking that calling to the repo returns an observable
    private fun stubGetProjects(observable: Observable<List<Project>>) {
        whenever(trendingProjectsRepository.getProjects())
            .thenReturn(observable)
    }
}