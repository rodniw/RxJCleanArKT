package dev.rodni.ru.cache.dao

import androidx.room.*
import dev.rodni.ru.cache.db.ProjectConstants.DELETE_PROJECTS
import dev.rodni.ru.cache.db.ProjectConstants.QUERY_BOOKMARKED_PROJECTS
import dev.rodni.ru.cache.db.ProjectConstants.QUERY_PROJECTS
import dev.rodni.ru.cache.db.ProjectConstants.QUERY_UPDATE_BOOKMARK_STATUS
import dev.rodni.ru.cache.model.CachedProject
import io.reactivex.Flowable

/**
 * Dao for operations with cached project model
 */
@Dao
abstract class CachedProjectsDao {

    /**
     * fetching cached projects
     *
     * return type is flowable in case of backpressure
     */
    @Query(QUERY_PROJECTS)
    abstract fun getProjects(): Flowable<List<CachedProject>>

    /**
     * this function inserts list of cached projects
     *
     * in case of duplicates an item will be replaced
     * because i want to have only the newest verstion of an item
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProjects(projects: List<CachedProject>)

    /**
     * this function deletes all the projects that are cached
     */
    @Delete
    abstract fun deleteProjects(projects: List<CachedProject>)

    /**
     * this function fetching only bookmarked projects
     */
    @Query(QUERY_BOOKMARKED_PROJECTS)
    abstract fun getBookmarkedProjects(): Flowable<List<CachedProject>>

    /**
     * this function update project's status
     *
     * to bookmarked/unbookmarked
     */
    @Query(QUERY_UPDATE_BOOKMARK_STATUS)
    abstract fun setBookmarkStatus(isBookmarked: Boolean,
                                   projectId: String)
}