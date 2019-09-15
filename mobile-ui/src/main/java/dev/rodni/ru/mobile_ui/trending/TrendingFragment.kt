package dev.rodni.ru.mobile_ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.android.support.DaggerFragment
import dev.rodni.ru.mobile_ui.R
import dev.rodni.ru.mobile_ui.model.Project
import kotlinx.android.synthetic.main.fragment_trending.*
import timber.log.Timber

/**
 * main fragment which shows list of trending projects
 */
class TrendingFragment: DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_trending, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun initRecyclerView(items: List<ProjectItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        recycler_projects.apply {
            layoutManager = LinearLayoutManager(this@TrendingFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            Timber.d("clicked")
        }
    }
}