package dev.rodni.ru.mobile_ui.di

import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import dev.rodni.ru.data.TrendingProjectsDataRepository
import javax.inject.Singleton

/**
 * mock version of
 * @see DataModule
 */
@Module
object TestDataModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideDataRepository(): TrendingProjectsDataRepository {
        return mock()
    }

}