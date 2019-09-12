package dev.rodni.ru.cache.db

/**
 * constants only object for configs
 */
object ConfigConstants {

    /**
     * defines a table name
     */
    const val TABLE_NAME = "config"

    /**
     * sql request that fetches data from the config table
     */
    const val QUERY_CONFIG = "SELECT * FROM $TABLE_NAME"

}