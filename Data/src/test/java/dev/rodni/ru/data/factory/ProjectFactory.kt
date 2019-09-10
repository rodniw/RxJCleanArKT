package dev.rodni.ru.data.factory

import dev.rodni.ru.data.model.ProjectEntity
import dev.rodni.ru.domain.model.Project

/**
 * object which provides fake project pojos and entities
 * uses
 * @see DataFactory
 * to generate random values
 */
object ProjectFactory {

    /**
     * entity to test mapper's things
     */
    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomBoolean())
    }

    /**
     * pojo to test mapper's things
     */
    fun makeProject(): Project {
        return Project(DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomBoolean())
    }
}