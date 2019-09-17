package dev.rodni.ru.mobile_ui.trending

/**
 * this for handling clicks on TrendingAdapter's items
 */
interface ProjectListener {

    fun onBookmarkedProjectClicked(projectId: String)

    fun onProjectClicked(projectId: String)

}