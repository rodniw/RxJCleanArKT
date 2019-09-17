package dev.rodni.ru.mobile_ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import dev.rodni.ru.mobile_ui.R
import dev.rodni.ru.mobile_ui.di.ViewModelFactory
import dev.rodni.ru.mobile_ui.mapper.ProjectViewMapper
import dev.rodni.ru.mobile_ui.model.Project
import dev.rodni.ru.presentation.model.ProjectView
import dev.rodni.ru.presentation.state.Resource
import dev.rodni.ru.presentation.state.ResourceState
import dev.rodni.ru.presentation.viewmodel.BrowseProjectsViewModel
import kotlinx.android.synthetic.main.fragment_bookmarked.*
import kotlinx.android.synthetic.main.fragment_trending.*
import kotlinx.android.synthetic.main.fragment_trending.recycler_projects
import javax.inject.Inject

/**
 * main fragment which shows list of trending projects
 */
class TrendingFragment: Fragment() {

    @Inject lateinit var browseAdapter: TrendingAdapter
    @Inject lateinit var mapper: ProjectViewMapper
    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var browseViewModel: BrowseProjectsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_trending, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidInjection.inject(activity)

        /**
         * sets up a view model
         */
        browseViewModel = ViewModelProvider(this@TrendingFragment, viewModelFactory)
            .get(BrowseProjectsViewModel::class.java)

        /**
         * sets up the recycler view
         */
        setupBrowseRecycler()
    }

    override fun onStart() {
        super.onStart()
        browseViewModel.getProjects().observe(this,
            Observer<Resource<List<ProjectView>>> {
                it?.let {
                    handleDataState(it)
                }
            })
        browseViewModel.fetchProjects()
    }

    /**
     * recycler view set up
     */
    private fun setupBrowseRecycler() {
        browseAdapter.projectListener = projectListener
        recycler_projects.layoutManager = LinearLayoutManager(activity)
        recycler_projects.adapter = browseAdapter
    }

    /**
     * handles loading view state
     *
     * in case of loading this hides the recycler view
     * and shows the loading state with the shimmer animation
     */
    private fun handleDataState(resource: Resource<List<ProjectView>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                setupScreenForSuccess(resource.data?.map {
                    mapper.mapToView(it)
                })
            }
            ResourceState.LOADING -> {
                recycler_projects.visibility = View.GONE
                homeShimmer.visibility = View.VISIBLE
                //homeLogo.visibility = View.GONE
                homeShimmer.startShimmer()
            }
        }
    }

    /**
     * makes recycler view visible
     */
    private fun setupScreenForSuccess(projects: List<Project>?) {
        homeShimmer.visibility = View.GONE
        homeShimmer.stopShimmer()
        projects?.let {
            browseAdapter.projects = it
            browseAdapter.notifyDataSetChanged()
            recycler_projects.visibility = View.VISIBLE
        } ?: run {

        }
    }

    /**
     * handles clicks on items
     */
    private val projectListener = object : ProjectListener {
        override fun onBookmarkedProjectClicked(projectId: String) {
            browseViewModel.unbookmarkProject(projectId)
        }

        override fun onProjectClicked(projectId: String) {
            browseViewModel.bookmarkProject(projectId)
        }
    }
}