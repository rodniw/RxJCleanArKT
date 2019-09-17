package dev.rodni.ru.data.repository

import dev.rodni.ru.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Flowable

import io.reactivex.Observable

/**
 * remote layer data fetch interface
 */
interface ProjectsRemote {

    /**
     * fetch projects from the github by internet
     */
    fun getProjects(): Flowable<List<ProjectEntity>>

}