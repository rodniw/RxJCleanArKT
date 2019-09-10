package dev.rodni.ru.data.store

import dev.rodni.ru.data.repository.ProjectsDataStore
import javax.inject.Inject

/**
 * this factory class is to handle a decision which implementation of a project's data source to use
 * two cases:
 * 1. cache
 * 2. remote
 */
open class ProjectsDataStoreFactory @Inject constructor(
    private val projectsCacheDataStore: ProjectsCacheDataStore,
    private val projectsRemoteDataStore: ProjectsRemoteDataStore
) {

    /**
     * if provided projectsCached == true and cache is not expired yet
     * then returns projectsCacheDataStore to use
     *
     * if provided projectsCached != true or cache already expired
     * then this method will return projectsRemoteDataStore one to use
     */
    open fun getDataStore(projectsCached: Boolean,
                          cacheExpired: Boolean): ProjectsDataStore {
        return if (projectsCached && !cacheExpired) {
            projectsCacheDataStore
        } else {
            projectsRemoteDataStore
        }
    }

    /**
     * use this method if exactly will be used cached data store
     * for example:
     * for bookmarks which does not need any network and can use cache data store then
     */
    open fun getCacheDataStore(): ProjectsDataStore {
        return projectsCacheDataStore
    }
}