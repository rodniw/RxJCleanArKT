package dev.rodni.ru.mobile_ui

import android.app.Application
import timber.log.Timber

/**
 * main application class
 */
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    /**
     * sets up timber logs
     */
    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}