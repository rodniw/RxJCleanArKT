package dev.rodni.ru.data.repository

import dev.rodni.ru.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectsDataStore {
    //fetches projects
    fun getProjects(): Observable<List<ProjectEntity>>

    //fetching bookmarked projects
    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    //setting a project as bookmarked
    fun setProjectAsBookmarked(projectId: String): Completable

    //setting a project not bookmarked anymore
    fun setProjectNotBookmarked(projectId: String): Completable

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun clearProjects(): Completable
}