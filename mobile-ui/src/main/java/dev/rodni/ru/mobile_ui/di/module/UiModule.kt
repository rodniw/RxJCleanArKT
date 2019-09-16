package dev.rodni.ru.mobile_ui.di.module

import dagger.Binds
import dagger.Module
import dev.rodni.ru.domain.executor.PostExecutionThread
import dev.rodni.ru.mobile_ui.UIThread

@Module
abstract class UiModule {

    /**
     * provides post execution thread through for ui showing
     */
    @Binds
    abstract fun bindPostExecutionThread(uiThread: UIThread): PostExecutionThread
}