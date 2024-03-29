package dev.rodni.ru.remote.mapper

import dev.rodni.ru.data.model.ProjectEntity
import dev.rodni.ru.remote.model.ProjectModel
import javax.inject.Inject

/**
 * implements interface
 * @see ModelMapper
 * this class to map Project models/entities
 */
open class ProjectsResponseModelMapper @Inject constructor(): ModelMapper<ProjectModel, ProjectEntity> {
    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(model.id, model.name, model.fullName, model.starCount.toString(),
            model.dateCreated, model.owner.ownerName, model.owner.ownerAvatar, false)
    }


}