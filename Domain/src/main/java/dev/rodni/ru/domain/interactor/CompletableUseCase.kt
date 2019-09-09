package dev.rodni.ru.domain.interactor

import dev.rodni.ru.domain.executor.PostExecutionThread
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

//base completable use case abstract class
abstract class CompletableUseCase<in Params> constructor(
    private val postExecutionThread: PostExecutionThread
) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseCompletable(params: Params? = null): Completable

    open fun execute(obseerver: DisposableCompletableObserver, params: Params? = null) {
        val completable = this.buildUseCaseCompletable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
        addDisposable(completable.subscribeWith(obseerver))
    }

    //a way to add a disposable into the composite disposable
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    //deleting disposables
    fun dispose() {
        disposables.dispose()
    }
}