package dev.rodni.ru.mobile_ui.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.rodni.ru.mobile_ui.MainActivity

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [
        FragmentBuilderModule::class
    ])
    abstract fun contributeMainActivity(): MainActivity
}