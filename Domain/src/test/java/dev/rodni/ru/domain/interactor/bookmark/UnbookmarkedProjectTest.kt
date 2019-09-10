package dev.rodni.ru.domain.interactor.bookmark

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import dev.rodni.ru.domain.executor.PostExecutionThread
import dev.rodni.ru.domain.interactor.bookmarked.UnbookmarkProject
import dev.rodni.ru.domain.repository.ProjectsRepository
import dev.rodni.ru.domain.test.ProjectDataFactory
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

//this test class is testing UnookmarkProject use case
class UnbookmarkedProjectTest {

    private lateinit var unbookmarkProject: UnbookmarkProject
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var executionThread: PostExecutionThread

    //setting up the constructor's dependencies
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        unbookmarkProject = UnbookmarkProject(projectsRepository, executionThread)
    }

    //needs parameters inside buildUseCase method
    @Test
    fun unbookmarkProjectShouldCompletes() {
        stubUnbookmarkProject(Completable.complete())
        val testObserver = unbookmarkProject.buildUseCaseCompletable(
            UnbookmarkProject.Params.forProject(ProjectDataFactory.randomUuid())
        ).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun unbookmarkProjectShouldThrowIllegalArgException() {
        unbookmarkProject.buildUseCaseCompletable().test()
    }

    //checking that calling to the repo returns an observable
    private fun stubUnbookmarkProject(completable: Completable) {
        whenever(projectsRepository.unbookmarkProject(any()))
            .thenReturn(completable)
    }
}