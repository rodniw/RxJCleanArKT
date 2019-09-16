package dev.rodni.ru.mobile_ui.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import dev.rodni.ru.presentation.viewmodel.BrowseBookmarkedProjectsViewModel
import dev.rodni.ru.presentation.viewmodel.BrowseProjectsViewModel
import kotlin.reflect.KClass

/**
 * provides view model's dependencies
 */
@Module
abstract class PresentationModule {

    /**
     * provides BrowseProjectsViewModel
     */
    @Binds
    @IntoMap
    @ViewModelKey(BrowseProjectsViewModel::class)
    abstract fun bindBrowseProjectsViewModel(viewModel: BrowseProjectsViewModel): ViewModel

    /**
     * provides BrowseBookmarkedProjectsViewModel
     */
    @Binds
    @IntoMap
    @ViewModelKey(BrowseBookmarkedProjectsViewModel::class)
    abstract fun bindBrowseBookmarkedProjectsViewModel(viewModel: BrowseBookmarkedProjectsViewModel): ViewModel
}

/**
 * annotation for binding view models
 */
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)