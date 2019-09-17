package dev.rodni.ru.mobile_ui.factory

import dev.rodni.ru.presentation.model.ProjectView

/**
 * factory for creating fake projects
 */
object ProjectFactory {

    fun makeProjectView(): ProjectView {
        return ProjectView(DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomBoolean())
    }

}