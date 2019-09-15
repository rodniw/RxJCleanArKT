package dev.rodni.ru.mobile_ui

import dev.rodni.ru.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * tells where to show executed funs
 */
class UIThread @Inject constructor(

): PostExecutionThread {
    /**
     * sets up the ui thread as a thread where i will show data
     */
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}