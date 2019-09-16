package dev.rodni.ru.mobile_ui.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    /**
     * provides context through the app
     */
    @Binds
    abstract fun bindContext(application: Application): Context
}