package dev.rodni.ru.mobile_ui.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dev.rodni.ru.mobile_ui.MyApplication
import javax.inject.Singleton

/**
 * the main app's component
 */
@Singleton
@Component(modules = [
AndroidInjectionModule::class
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