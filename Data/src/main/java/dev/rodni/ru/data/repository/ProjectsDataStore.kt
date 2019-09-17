package dev.rodni.ru.data.repository

import dev.rodni.ru.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface ProjectsDataStore {
    //fetches projects
    fun getProjects(): Flowable<List<ProjectEntity>>

    //fetching bookmarked projects
    fun getBookmarkedProjects(): Flowable<List<ProjectEntity>>

    //setting a project as bookmarked
    fun setProjectAsBookmarked(projectId: String): Completable

    //setting a project not bookmarked anymore
    fun setProjectNotBookmarked(projectId: String): Completable

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun clearProjects(projects: List<ProjectEntity>): Completable
}