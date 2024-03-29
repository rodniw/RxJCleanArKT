package dev.rodni.ru.domain.interactor.bookmarked

import dev.rodni.ru.domain.executor.PostExecutionThread
import dev.rodni.ru.domain.interactor.CompletableUseCase
import dev.rodni.ru.domain.repository.TrendingProjectsRepository
import io.reactivex.Completable
import javax.inject.Inject

open class UnbookmarkProject @Inject constructor(
    private val trendingProjectsRepository: TrendingProjectsRepository,
    executionThread: PostExecutionThread
) : CompletableUseCase<UnbookmarkProject.Params>(executionThread) {

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Passed nullable params")
        return trendingProjectsRepository.unbookmarkProject(params.projectId)
    }

    //this is for giving params to the repository
    data class Params constructor(val projectId: String) {
        companion object {
            fun forProject(projectId: String) : Params {
                return Params(projectId)
            }
        }
    }
}