package dev.rodni.ru.mobile_ui.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import dev.rodni.ru.data.repository.ProjectsRemote
import dev.rodni.ru.mobile_ui.BuildConfig
import dev.rodni.ru.remote.ProjectsRemoteImpl
import dev.rodni.ru.remote.service.GithubTrendingService
import dev.rodni.ru.remote.service.GithubTrendingServiceFactory

@Module
abstract class RemoteModule {

    /**
     * returns companion object which created the service
     */
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): GithubTrendingService {
            return GithubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    /**
     * binds implementation with its abstraction
     */
    @Binds
    abstract fun bindProjectsRemote(projectsRemote: ProjectsRemoteImpl): ProjectsRemote
}