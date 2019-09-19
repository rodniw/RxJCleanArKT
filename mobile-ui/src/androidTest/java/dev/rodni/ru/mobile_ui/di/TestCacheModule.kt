package dev.rodni.ru.mobile_ui.di

import android.app.Application
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import dev.rodni.ru.cache.db.AppDatabase
import dev.rodni.ru.data.repository.ProjectsCache

/**
 * mock cache module
 */
@Module
object TestCacheModule {

    @Provides
    @JvmStatic
    fun provideDatabase(application: Application): AppDatabase {
        return AppDatabase.getInstance(application)
    }

    /**
     * returns mock
     */
    @Provides
    @JvmStatic
    fun provideProjectsCache(): ProjectsCache {
        return mock()
    }

}