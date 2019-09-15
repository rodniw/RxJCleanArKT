package dev.rodni.ru.mobile_ui.navigation

/**
 * base navigator through app's pages
 */
interface BaseNavigator {

    fun navigateToHome()

    fun navigateToEvent()

    fun getEvent()
}