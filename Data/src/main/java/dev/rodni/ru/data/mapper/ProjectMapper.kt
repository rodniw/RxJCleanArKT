package dev.rodni.ru.data.mapper

import dev.rodni.ru.data.model.ProjectEntity
import dev.rodni.ru.domain.model.Project
import javax.inject.Inject

//mapper for the ProjectEntity
open class ProjectMapper @Inject constructor(): EntityMapper<ProjectEntity, Project> {
    override fun mapFromEntity(entity: ProjectEntity): Project {
        return Project(entity.id, entity.name, entity.fullName,
            entity.starCount, entity.dateCreated, entity.ownerName,
            entity.ownerAvatar, entity.isBookmarked)
    }

    override fun mapToEntity(domain: Project): ProjectEntity {
        return ProjectEntity(domain.id, domain.name, domain.fullName,
            domain.starCount, domain.dateCreated, domain.ownerName,
            domain.ownerAvatar, domain.isBookmarked)
    }
}