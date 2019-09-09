package dev.rodni.ru.domain.executor

import io.reactivex.Scheduler

//i dont want the module to know about the thing that im using rxandroid
interface PostExecutionThread {
    val scheduler: Scheduler
}