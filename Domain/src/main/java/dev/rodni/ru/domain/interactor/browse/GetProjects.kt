package dev.rodni.ru.domain.interactor.browse

import dev.rodni.ru.domain.executor.PostExecutionThread
import dev.rodni.ru.domain.interactor.ObservableUseCase
import dev.rodni.ru.domain.model.Project
import dev.rodni.ru.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

//i expect list of projects back and i dont have any params
//also im calling parents constructor and giving my post execution thread from my constructor to parent's constructor
open class GetProjects @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread){

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }

}