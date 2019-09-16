package dev.rodni.ru.mobile_ui.di.module

import dagger.Binds
import dagger.Module
import dev.rodni.ru.data.ProjectsDataRepository
import dev.rodni.ru.domain.repository.ProjectsRepository

@Module
abstract class DataModule {

    /**
     * binds realization with its abstraction
     */
    @Binds
    abstract fun bindDataRepository(dataRepository: ProjectsDataRepository): ProjectsRepository
}