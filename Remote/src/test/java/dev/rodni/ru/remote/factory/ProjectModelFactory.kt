package dev.rodni.ru.remote.factory

import dev.rodni.ru.data.model.ProjectEntity
import dev.rodni.ru.remote.model.OwnerModel
import dev.rodni.ru.remote.model.ProjectModel
import dev.rodni.ru.remote.model.ProjectsResponseModel

/**
 * this wil create fake project models for tests
 */
object ProjectModelFactory {

    fun makeOwnerModel(): OwnerModel {
        return OwnerModel(DataFactory.randomUuid(), DataFactory.randomUuid())
    }

    /**
     * creates fake project model
     */
    fun makeProject(): ProjectModel {
        return ProjectModel(DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomInt(),
            DataFactory.randomUuid(), makeOwnerModel())
    }

    /**
     * creates fake project entity to test project's mapper
     */
    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomBoolean())
    }

    /**
     * creates fake project's response to test service
     */
    fun makeProjectsResponse(): ProjectsResponseModel {
        return ProjectsResponseModel(listOf(makeProject(), makeProject()))
    }
}