package dev.rodni.ru.mobile_ui.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dev.rodni.ru.mobile_ui.MyApplication
import dev.rodni.ru.mobile_ui.di.module.ActivityBuilderModule
import dev.rodni.ru.mobile_ui.di.module.ApplicationModule
import dev.rodni.ru.mobile_ui.di.module.PresentationModule
import dev.rodni.ru.mobile_ui.di.module.UiModule
import javax.inject.Singleton

/**
 * the main app's component
 */
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApplicationModule::class,
    UiModule::class,
    PresentationModule::class,
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