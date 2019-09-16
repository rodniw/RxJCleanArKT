package dev.rodni.ru.domain.repository

import dev.rodni.ru.domain.model.Project
import dev.rodni.ru.domain.model.User
import io.reactivex.Observable

/**
 * searching repository
 */
interface SearchProjectsRepository {

    /**
     * fetching projects with search response
     */
    fun searchProjects(): Observable<List<Project>>

    /**
     * fetching list of searched users
     */
    fun searchUsers(): Observable<List<User>>
}