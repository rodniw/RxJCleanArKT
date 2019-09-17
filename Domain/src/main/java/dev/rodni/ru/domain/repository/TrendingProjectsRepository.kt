package dev.rodni.ru.domain.repository

import dev.rodni.ru.domain.model.Project
import dev.rodni.ru.domain.model.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

//this will be implemented by data layer
interface TrendingProjectsRepository {
    //function which will donwload repos from rest api
    fun getProjects(): Flowable<List<Project>>

    //function which will put a project into a saved list
    fun bookmarkProject(projectId: String): Completable

    //this will delete project from saved list
    fun unbookmarkProject(projectId: String): Completable

    //this will show list of saved projects
    fun getBookmarkedProjects(): Flowable<List<Project>>
}