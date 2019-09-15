package dev.rodni.ru.mobile_ui.mapper

import dev.rodni.ru.mobile_ui.model.Project
import dev.rodni.ru.presentation.model.ProjectView
import javax.inject.Inject

/**
 * project pojo mapper
 */
class ProjectViewMapper @Inject constructor(): ViewMapper<ProjectView, Project> {
    override fun mapToView(presentation: ProjectView): Project {
        return Project(presentation.id, presentation.name,
            presentation.fullName, presentation.starCount,
            presentation.dateCreated, presentation.ownerName,
            presentation.ownerAvatar, presentation.isBookmarked)
    }

}