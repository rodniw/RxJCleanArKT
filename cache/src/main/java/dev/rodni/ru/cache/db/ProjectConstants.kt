package dev.rodni.ru.cache.db

/**
 * this object holds constants for working with the db
 */
object ProjectConstants {
    /**
     * cache project model table name
     * @see CachedProject
     */
    const val TABLE_NAME = "projects"

    /**
     * column name of the project_id field
     * @see CachedProject
     */
    const val COLUMN_PROJECT_ID = "project_id"

    /**
     * column name of the is_bookmarked field
     * @see CachedProject
     *
     * i need this field to fetch only bookmarked projects
     */
    const val COLUMN_IS_BOOKMARKED = "is_bookmarked"

    /**
     * query for
     * @see CachedProjectsDao
     * by this i fetch models
     */
    const val QUERY_PROJECTS = "SELECT * FROM $TABLE_NAME"

    /**
     * sql request for deleting the projects
     * @see CachedProjectsDao
     */
    const val DELETE_PROJECTS = "DELETE * FROM $TABLE_NAME"

    /**
     * sql request for fetching only bookmarked projects
     * @see CachedProjectsDao
     */
    const val  QUERY_BOOKMARKED_PROJECTS = "SELECT * FROM $TABLE_NAME " +
            "WHERE $COLUMN_IS_BOOKMARKED = 1"

    /**
     * sql request that updates bookmark status
     */
    const val QUERY_UPDATE_BOOKMARK_STATUS = "UPDATE $TABLE_NAME " +
            "SET $COLUMN_IS_BOOKMARKED = :isBookmarked WHERE " +
            "$COLUMN_PROJECT_ID = :projectId"
}