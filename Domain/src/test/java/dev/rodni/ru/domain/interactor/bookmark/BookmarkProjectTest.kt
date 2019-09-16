package dev.rodni.ru.domain.interactor.bookmark

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import dev.rodni.ru.domain.executor.PostExecutionThread
import dev.rodni.ru.domain.interactor.bookmarked.BookmarkProject
import dev.rodni.ru.domain.repository.TrendingProjectsRepository
import dev.rodni.ru.domain.test.ProjectDataFactory
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

//this test class is testing BookmarkProject use case
class BookmarkProjectTest {

    private lateinit var bookmarkProject: BookmarkProject
    @Mock lateinit var trendingProjectsRepository: TrendingProjectsRepository
    @Mock lateinit var executionThread: PostExecutionThread

    //setting up the constructor's dependencies
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        bookmarkProject = BookmarkProject(trendingProjectsRepository, executionThread)
    }

    //needs parameters inside buildUseCase method
    @Test
    fun bookmarkProjectShouldCompletes() {
        stubBookmarkProject(Completable.complete())
        val testObserver = bookmarkProject.buildUseCaseCompletable(
            BookmarkProject.Params.forProject(ProjectDataFactory.randomUuid())
        ).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkProjectShouldThrowIllegalArgException() {
        bookmarkProject.buildUseCaseCompletable().test()
    }

    //checking that calling to the repo returns an observable
    private fun stubBookmarkProject(completable: Completable) {
        whenever(trendingProjectsRepository.bookmarkProject(any()))
            .thenReturn(completable)
    }
}