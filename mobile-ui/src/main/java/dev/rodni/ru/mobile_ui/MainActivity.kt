package dev.rodni.ru.mobile_ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import dagger.android.support.DaggerAppCompatActivity
import dev.rodni.ru.mobile_ui.bookmarked.BookmarkedFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * bottom_main app's activity
 * which is a host for fragments
 * with navigation framework
 */
class MainActivity: DaggerAppCompatActivity() {

    /**
     * nav controller instance for navigation
     */
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        /**
         * sets up nav controller
         */
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bottom_nav.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    /**
     * back button for fragments
     */
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

}