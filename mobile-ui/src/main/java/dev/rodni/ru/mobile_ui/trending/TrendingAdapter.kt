package dev.rodni.ru.mobile_ui.trending

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.rodni.ru.mobile_ui.R
import dev.rodni.ru.mobile_ui.model.Project
import javax.inject.Inject

/**
 * classic recycler adapter for
 * @see TrendingFragment
 */
class TrendingAdapter @Inject constructor(): RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {

    var projects: List<Project> = arrayListOf()
    var projectListener: ProjectListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_project, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return projects.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projects[position]
        holder.ownerNameText.text = project.ownerName
        holder.projectNameText.text = project.fullName

        Glide.with(holder.itemView.context)
            .load(project.ownerAvatar)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.avatarImage)

        val starResource = if (project.isBookmarked) {
            R.drawable.ic_star_black_24dp
        } else {
            R.drawable.ic_star_border_black_24dp
        }
        holder.bookmarkedImage.setImageResource(starResource)

        holder.itemView.setOnClickListener {
            if (project.isBookmarked) {
                projectListener?.onBookmarkedProjectClicked(project.id)
            } else {
                projectListener?.onProjectClicked(project.id)
            }
        }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var avatarImage: ImageView = view.findViewById(R.id.image_owner_avatar)
        var ownerNameText: TextView = view.findViewById(R.id.text_owner_name)
        var projectNameText: TextView = view.findViewById(R.id.text_project_name)
        var bookmarkedImage: ImageView = view.findViewById(R.id.image_bookmarked)
    }

}