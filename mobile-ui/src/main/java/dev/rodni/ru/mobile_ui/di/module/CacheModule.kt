package dev.rodni.ru.mobile_ui.di.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import dev.rodni.ru.cache.ProjectsCacheImpl
import dev.rodni.ru.cache.db.AppDatabase
import dev.rodni.ru.data.repository.ProjectsCache

@Module
abstract class CacheModule {

    /**
     * returns companion object which created database instance
     */
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): AppDatabase {
            return AppDatabase.getInstance(application)
        }
    }

    /**
     * binds implementation with its abstraction
     */
    @Binds
    abstract fun bindProjectsCache(projectsCache: ProjectsCacheImpl): ProjectsCache
}