package dev.rodni.ru.mobile_ui.factory

import dev.rodni.ru.domain.model.Project

/**
 * factory for creating fake projects
 */
object TestProjectFactory {

    fun makeProject(): Project {
        return Project(TestDataFactory.randomUuid(), TestDataFactory.randomUuid(),
            TestDataFactory.randomUuid(), TestDataFactory.randomUuid(), TestDataFactory.randomUuid(),
            TestDataFactory.randomUuid(), TestDataFactory.randomUuid(), TestDataFactory.randomBoolean())
    }

}