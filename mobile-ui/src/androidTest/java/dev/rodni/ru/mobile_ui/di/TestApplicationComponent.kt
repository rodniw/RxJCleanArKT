package dev.rodni.ru.mobile_ui.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import dev.rodni.ru.domain.repository.TrendingProjectsRepository
import dev.rodni.ru.mobile_ui.di.module.PresentationModule
import dev.rodni.ru.mobile_ui.di.module.UiModule
import dev.rodni.ru.mobile_ui.test.TestApplication
import javax.inject.Singleton

/**
 * main test component
 */
@Singleton
@Component(modules = arrayOf(
    AndroidSupportInjectionModule::class,
    TestApplicationModule::class,
    TestCacheModule::class,
    TestDataModule::class,
    PresentationModule::class,
    UiModule::class,
    TestRemoteModule::class))
interface TestApplicationComponent {

    fun trendingProjectsRepository(): TrendingProjectsRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): TestApplicationComponent.Builder

        fun build(): TestApplicationComponent
    }

    fun inject(application: TestApplication)

}