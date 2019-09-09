package dev.rodni.ru.domain.repository

import dev.rodni.ru.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Observable

//this will be implemented by data layer
interface ProjectsRepository {
    //function which will donwload repos from rest api
    fun getProjects(): Observable<List<Project>>

    //function which will put a project into a saved list
    fun bookmarkProject(projectId: String): Completable

    //this will delete project from saved list
    fun unbookmarkProject(projectId: String): Completable

    //this will show list of saved projects
    fun getBookmarkedProjects(): Observable<List<Project>>
}