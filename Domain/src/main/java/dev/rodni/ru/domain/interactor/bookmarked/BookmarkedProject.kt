package dev.rodni.ru.domain.interactor.bookmarked

import dev.rodni.ru.domain.executor.PostExecutionThread
import dev.rodni.ru.domain.interactor.CompletableUseCase
import dev.rodni.ru.domain.repository.ProjectsRepository
import io.reactivex.Completable
import javax.inject.Inject

//when a use tap to save a project
open class BookmarkedProject @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<BookmarkedProject.Params>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params cant be null")
        return projectsRepository.bookmarkProject(params.projectId)
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