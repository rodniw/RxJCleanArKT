package dev.rodni.ru.mobile_ui.trending

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import dev.rodni.ru.mobile_ui.R
import dev.rodni.ru.mobile_ui.model.Project
import kotlinx.android.synthetic.main.item_project.*

class ProjectItem(
    val project: Project
): Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            updateProjectOwner()
            updateProjectTitle()
            updateConditionImage()
        }
    }

    override fun getLayout() = R.layout.item_project

    private fun ViewHolder.updateProjectOwner() {
        text_owner_name.text = project.ownerName
    }

    private fun ViewHolder.updateProjectTitle() {
        text_project_name.text = project.name
    }

    private fun ViewHolder.updateConditionImage() {
        //GlideApp.with(this.containerView)
        //    .load("http:" + weatherEntry.conditionIconUrl)
        //    .into(imageView_condition_icon)
    }
}