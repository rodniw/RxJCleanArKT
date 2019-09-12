package dev.rodni.ru.cache.mapper

import dev.rodni.ru.cache.model.CachedProject
import dev.rodni.ru.data.model.ProjectEntity

/**
 * implements
 * @see CacheMapper
 *
 * to find out which method what should do go there
 */
class CachedProjectMapper: CacheMapper<CachedProject, ProjectEntity> {
    override fun mapFromCached(type: CachedProject): ProjectEntity {
        return ProjectEntity(type.id, type.name, type.fullName, type.starCount,
            type.dateCreated, type.ownerName, type.ownerAvatar,
            type.isBookmarked)
    }

    override fun mapToCached(type: ProjectEntity): CachedProject {
        return CachedProject(type.id, type.name, type.fullName, type.starCount,
            type.dateCreated, type.ownerName, type.ownerAvatar,
            type.isBookmarked)
    }
}