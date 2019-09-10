package dev.rodni.ru.data.repository

import dev.rodni.ru.data.model.ProjectEntity

import io.reactivex.Observable

//remote level
interface ProjectsRemote {

    //fetch projects from the github by internet
    fun getProjects(): Observable<List<ProjectEntity>>
}