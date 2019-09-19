package dev.rodni.ru.mobile_ui.di

import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import dev.rodni.ru.data.repository.ProjectsRemote
import dev.rodni.ru.remote.service.GithubTrendingService

/**
 * mock for testing
 * @see RemoteModule
 */
@Module
object TestRemoteModule {

    @Provides
    @JvmStatic
    fun provideGithubService(): GithubTrendingService {
        return mock()
    }

    @Provides
    @JvmStatic
    fun provideProjectsRemote(): ProjectsRemote {
        return mock()
    }

}