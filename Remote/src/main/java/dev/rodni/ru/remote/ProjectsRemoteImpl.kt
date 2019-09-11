package dev.rodni.ru.remote

import dev.rodni.ru.data.model.ProjectEntity
import dev.rodni.ru.data.repository.ProjectsRemote
import dev.rodni.ru.remote.mapper.ProjectsResponseModelMapper
import dev.rodni.ru.remote.service.GithubTrendingService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * this class gives me the implementation of the interface
 * @see ProjectsRemote from Data layer
 * and here it's going to be remote requests
 *
 * the constructor takes two arguments
 * 1. retrofit service to fetch data from the internet
 * 2. project's model mapper to give it to the Data layer
 */
class ProjectsRemoteImpl @Inject constructor(
    private val service: GithubTrendingService,
    private val mapper: ProjectsResponseModelMapper
): ProjectsRemote {

    /**
     * overrides method from
     * @see ProjectsRemote
     * to fetch remote data from the internet
     */
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
            .map {
                it.items.map { mapper.mapFromModel(it) }
            }
    }
}