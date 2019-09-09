package dev.rodni.ru.domain.interactor.bookmarked

import dev.rodni.ru.domain.executor.PostExecutionThread
import dev.rodni.ru.domain.interactor.ObservableUseCase
import dev.rodni.ru.domain.model.Project
import dev.rodni.ru.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetBookmarkedProjects @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread){

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getBookmarkedProjects()
    }

}