package dev.rodni.ru.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.rodni.ru.cache.dao.CachedProjectsDao
import dev.rodni.ru.cache.dao.ConfigDao
import dev.rodni.ru.cache.model.CachedProject
import dev.rodni.ru.cache.model.Config
import javax.inject.Inject

/**
 * database of this app
 */
@Database(entities = [CachedProject::class, Config::class], version = 1)
abstract class AppDatabase @Inject constructor(): RoomDatabase() {

    /**
     * setting up daos to make them accessable from the app database
     */
    abstract fun cachedProjectsDao(): CachedProjectsDao
    abstract fun confidDao(): ConfigDao

    companion object {

        /**
         * var for singleton
         */
        private var INSTANCE: AppDatabase? = null
        /**
         * val lock for safety threading
         */
        private val lock = Any()

        /**
         * classic getInstance function with singleton pattern
         *
         * this function uses synchronized(lock) for threading safety reason
         */
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "app.db")
                        .build()
                    }
                    return INSTANCE as AppDatabase
                }
            }
            return INSTANCE as AppDatabase
        }
    }
}