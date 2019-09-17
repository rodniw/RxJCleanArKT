package dev.rodni.ru.mobile_ui.trending

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.android.AndroidInjection
import dagger.android.DaggerFragment
import dev.rodni.ru.mobile_ui.R
import dev.rodni.ru.mobile_ui.di.ViewModelFactory
import dev.rodni.ru.mobile_ui.mapper.ProjectViewMapper
import dev.rodni.ru.presentation.model.ProjectView
import dev.rodni.ru.presentation.state.Resource
import dev.rodni.ru.presentation.viewmodel.BrowseProjectsViewModel
import kotlinx.android.synthetic.main.fragment_trending.*
import javax.inject.Inject

/**
 * main fragment which shows list of trending projects
 */
class TrendingFragment: Fragment() {

    @Inject lateinit var mapper: ProjectViewMapper
    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var browseViewModel: BrowseProjectsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_trending, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidInjection.inject(activity)

        browseViewModel = ViewModelProvider(this@TrendingFragment, viewModelFactory)
            .get(BrowseProjectsViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        browseViewModel.getProjects().observe(this@TrendingFragment,
            Observer<Resource<List<ProjectView>>> {

            })
        browseViewModel.fetchProjects()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
    }

    /**
     * recycler view with groupie
     */
    private fun initRecyclerView(items: List<ProjectItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        recycler_projects.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? ProjectItem)?.let {
                makeBookmarked()
            }
        }
    }

    private fun makeBookmarked() {

    }

    private fun handleDataState(resource: Resource<List<ProjectView>>) {

    }
}