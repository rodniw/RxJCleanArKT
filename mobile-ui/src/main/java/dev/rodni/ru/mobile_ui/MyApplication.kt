package dev.rodni.ru.mobile_ui

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dev.rodni.ru.mobile_ui.di.DaggerApplicationComponent
import timber.log.Timber
import javax.inject.Inject

/**
 * main application class
 */
class MyApplication: Application(), HasActivityInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()
        setupTimber()

        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }

    /**
     * sets up timber logs
     */
    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}