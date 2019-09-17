package dev.rodni.ru.data.repository

import dev.rodni.ru.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

//cache level
interface ProjectsCache {

    //to clear all the cache
    fun clearProjects(projects: List<ProjectEntity>): Completable

    //to save some projects
    fun saveProjects(projects: List<ProjectEntity>): Completable

    //fetches projects
    fun getProjects(): Flowable<List<ProjectEntity>>

    //fetching bookmarked projects
    fun getBookmarkedProjects(): Flowable<List<ProjectEntity>>

    //setting a project as bookmarked
    fun setProjectAsBookmarked(projectId: String): Completable

    //setting a project not bookmarked anymore
    fun setProjectNotBookmarked(projectId: String): Completable

    fun areProjectsCached(): Single<Boolean>

    //setting a time of a last cache operation
    fun setLastCacheTime(lastCache: Long): Completable

    fun isProjectsCacheExpired(): Single<Boolean>
}