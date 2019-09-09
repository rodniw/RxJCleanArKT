package dev.rodni.ru.domain.interactor

import dev.rodni.ru.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

//base observable use case abstract class
abstract class ObservableUseCase<T, in Params> constructor(
    private val postExecutionThread: PostExecutionThread
) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>

    open fun execute(obseerver: DisposableObserver<T>, params: Params? = null) {
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
        addDisposable(observable.subscribeWith(obseerver))
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