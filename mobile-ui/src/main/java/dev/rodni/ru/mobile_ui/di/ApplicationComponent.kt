package dev.rodni.ru.mobile_ui.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dev.rodni.ru.mobile_ui.MyApplication
import dev.rodni.ru.mobile_ui.di.module.*
import javax.inject.Singleton

/**
 * the bottom_main app's component
 */
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApplicationModule::class,
    UiModule::class,
    PresentationModule::class,
    DataModule::class,
    CacheModule::class,
    RemoteModule::class,
    ActivityBuilderModule::class
])
interface ApplicationComponent {

    /**
     * classic di builder interface
     */
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: MyApplication)
}