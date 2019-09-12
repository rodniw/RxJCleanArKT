package dev.rodni.ru.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.rodni.ru.cache.db.ProjectConstants

/**
 * an entity of cached project for my database
 *
 * the ID field is the primary key of this table
 */
@Entity(tableName = ProjectConstants.TABLE_NAME)
data class CachedProject (
    @PrimaryKey
    var id: String,
    var name: String,
    var fullName: String,
    var starCount: String,
    var dateCreated: String,
    var ownerName: String,
    var ownerAvatar: String,
    var isBookmarked: Boolean
)