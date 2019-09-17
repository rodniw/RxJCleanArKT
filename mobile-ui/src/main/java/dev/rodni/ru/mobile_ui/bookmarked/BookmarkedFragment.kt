package dev.rodni.ru.mobile_ui.bookmarked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import dagger.android.support.DaggerFragment
import dev.rodni.ru.mobile_ui.R
import dev.rodni.ru.mobile_ui.di.ViewModelFactory
import dev.rodni.ru.mobile_ui.mapper.ProjectViewMapper
import dev.rodni.ru.presentation.model.ProjectView
import dev.rodni.ru.presentation.state.Resource
import dev.rodni.ru.presentation.state.ResourceState
import dev.rodni.ru.presentation.viewmodel.BrowseBookmarkedProjectsViewModel
import kotlinx.android.synthetic.main.fragment_bookmarked.*
import javax.inject.Inject

/**
 * fragment shows list of bookmarked projects
 */
class BookmarkedFragment: DaggerFragment() {

    @Inject lateinit var adapter: BookmarkedAdapter
    @Inject lateinit var mapper: ProjectViewMapper
    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var browseViewModel: BrowseBookmarkedProjectsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bookmarked, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        AndroidInjection.inject(activity)

        browseViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(BrowseBookmarkedProjectsViewModel::class.java)

        setupBrowseRecycler()
    }

    /**
     * start observing for data changes
     */
    override fun onStart() {
        super.onStart()
        browseViewModel.getBookmarkedProjects().observe(this,
            Observer<Resource<List<ProjectView>>> {
                it?.let {
                    handleDataState(it)
                }
            })
        browseViewModel.fetchProjects()
    }

    /**
     * sets up recycler view for bookmarked fragment
     */
    private fun setupBrowseRecycler() {
        recycler_projects.layoutManager = LinearLayoutManager(activity)
        recycler_projects.adapter = adapter
    }

    /**
     * handles ui loading state
     */
    //todo change this to use shimmer
    private fun handleDataState(resource: Resource<List<ProjectView>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                progress.visibility = View.GONE
                recycler_projects.visibility = View.VISIBLE
                resource.data?.let {
                    adapter.projects = it.map { mapper.mapToView(it) }
                    adapter.notifyDataSetChanged()
                }
            }
            ResourceState.LOADING -> {
                progress.visibility = View.VISIBLE
                recycler_projects.visibility = View.GONE
            }
        }
    }

}