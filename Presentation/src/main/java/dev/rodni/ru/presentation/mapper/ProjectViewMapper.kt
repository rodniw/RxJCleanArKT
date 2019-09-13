package dev.rodni.ru.presentation.mapper

import dev.rodni.ru.domain.model.Project
import dev.rodni.ru.presentation.model.ProjectView
import javax.inject.Inject

/**
 * mapper of the
 * @see ProjectView
 * which convert incoming pojo into the project view
 */
open class ProjectViewMapper @Inject constructor(): Mapper<ProjectView, Project> {

    /**
     * maps incoming pojo into the view
     */
    override fun mapToView(type: Project): ProjectView {
        return ProjectView(type.id, type.name, type.fullName,
            type.starCount, type.dateCreated, type.ownerName,
            type.ownerAvatar, type.isBookmarked)
    }
}