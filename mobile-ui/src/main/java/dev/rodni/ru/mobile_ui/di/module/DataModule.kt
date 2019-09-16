package dev.rodni.ru.mobile_ui.di.module

import dagger.Binds
import dagger.Module
import dev.rodni.ru.data.TrendingProjectsDataRepository
import dev.rodni.ru.domain.repository.TrendingProjectsRepository

@Module
abstract class DataModule {

    /**
     * binds realization with its abstraction
     */
    @Binds
    abstract fun bindDataRepository(dataRepository: TrendingProjectsDataRepository): TrendingProjectsRepository
}