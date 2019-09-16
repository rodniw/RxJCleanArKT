package dev.rodni.ru.mobile_ui.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.rodni.ru.mobile_ui.bookmarked.BookmarkedFragment
import dev.rodni.ru.mobile_ui.trending.TrendingFragment

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeTrendingFragment(): TrendingFragment

    @ContributesAndroidInjector
    abstract fun contributeBookmarkedFragment(): BookmarkedFragment
}