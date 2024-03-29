package dev.rodni.ru.mobile_ui.mapper

import dev.rodni.ru.mobile_ui.factory.ProjectFactory
import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * module's mapper test
 */
class ProjectMapperTest {

    private val projectMapper = ProjectViewMapper()

    @Test
    fun mapToViewShouldMapsData() {
        val project = ProjectFactory.makeProjectView()
        val projectForUi = projectMapper.mapToView(project)

        assertEquals(project.id, projectForUi.id)
        assertEquals(project.name, projectForUi.name)
        assertEquals(project.fullName, projectForUi.fullName)
        assertEquals(project.starCount, projectForUi.starCount)
        assertEquals(project.dateCreated, projectForUi.dateCreated)
        assertEquals(project.ownerName, projectForUi.ownerName)
        assertEquals(project.ownerAvatar, projectForUi.ownerAvatar)
        assertEquals(project.isBookmarked, projectForUi.isBookmarked)
    }

}