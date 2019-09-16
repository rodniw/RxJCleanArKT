package dev.rodni.ru.mobile_ui.trending

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import dev.rodni.ru.mobile_ui.R
import dev.rodni.ru.mobile_ui.model.Project
import kotlinx.android.synthetic.main.item_project.*

/**
 * item for Trending Fragment recycler view
 */
class ProjectItem(
    val project: Project
): Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            updateProjectOwner()
            updateProjectTitle()
            updateOwnerAvatar()
            updateBookmarkImg()
        }
    }

    /**
     * inflates layout
     */
    override fun getLayout() = R.layout.item_project

    /**
     * sets up project owner name
     */
    private fun ViewHolder.updateProjectOwner() {
        text_owner_name.text = project.ownerName
    }

    /**
     * sets up project title
     */
    private fun ViewHolder.updateProjectTitle() {
        text_project_name.text = project.name
    }

    /**
     * sets up project owner's avatar image
     * with Glide
     */
    private fun ViewHolder.updateOwnerAvatar() {
        Glide.with(this.containerView)
            .load(project.ownerAvatar)
            .apply(RequestOptions.circleCropTransform())
            .into(image_owner_avatar)
    }

    /**
     * checks for boolean is bookmarked to set empty or full star with a project
     */
    private fun ViewHolder.updateBookmarkImg() {
        val starResource = if (project.isBookmarked) {
            R.drawable.ic_star_black_24dp
        } else {
            R.drawable.ic_star_border_black_24dp
        }
        image_bookmarked.setImageResource(starResource)
    }
}