package dev.rodni.ru.domain.interactor.bookmarked

import dev.rodni.ru.domain.executor.PostExecutionThread
import dev.rodni.ru.domain.interactor.ObservableUseCase
import dev.rodni.ru.domain.model.Project
import dev.rodni.ru.domain.repository.TrendingProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetBookmarkedProjects @Inject constructor(
    private val trendingProjectsRepository: TrendingProjectsRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread){

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return trendingProjectsRepository.getBookmarkedProjects()
    }

}