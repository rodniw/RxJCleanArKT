package dev.rodni.ru.cache.factory

import dev.rodni.ru.cache.model.CachedProject
import dev.rodni.ru.data.model.ProjectEntity

/**
 * provides fake project pojos
 */
object ProjectDataFactory {

    /**
     * creates cached project pojo
     */
    fun makeCachedProject(): CachedProject {
        return CachedProject(DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            false)
    }

    /**
     * creates cached bookmarked project pojo
     */
    fun makeBookmarkedCachedProject(): CachedProject {
        return CachedProject(DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            true)
    }

    /**
     * creates project entity(data module)
     */
    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomBoolean())
    }

    /**
     * creates bookmarked project entity(data module)
     */
    fun makeBookmarkedProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            true)
    }
}