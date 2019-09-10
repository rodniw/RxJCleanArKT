package dev.rodni.ru.data.mapper

import dev.rodni.ru.data.factory.ProjectFactory
import dev.rodni.ru.data.model.ProjectEntity
import dev.rodni.ru.domain.model.Project
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

/**
 * this class is for testing data layer's project entity mapper
 */
@RunWith(JUnit4::class)
class ProjectMapperTest {

    private val mapper = ProjectMapper()

    /**
     * this tests from entity to pojo
     */
    @Test
    fun mapFromEntityMapsData() {
        val entity = ProjectFactory.makeProjectEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    /**
     * this tests from pojo to entity
     */
    @Test
    fun mapToEntityMapsData() {
        val model = ProjectFactory.makeProject()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }

    /**
     * helper method which compare entity's field with pojo's field
     * line by line for every property
     */
    private fun assertEqualData(entity: ProjectEntity,
                                model: Project) {
        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.name)
        assertEquals(entity.fullName, model.fullName)
        assertEquals(entity.starCount, model.starCount)
        assertEquals(entity.dateCreated, model.dateCreated)
        assertEquals(entity.ownerName, model.ownerName)
        assertEquals(entity.ownerAvatar, model.ownerAvatar)
        assertEquals(entity.isBookmarked, model.isBookmarked)
    }
}