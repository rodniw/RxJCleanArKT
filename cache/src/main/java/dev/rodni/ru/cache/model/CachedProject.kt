package dev.rodni.ru.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.rodni.ru.cache.db.ProjectConstants

/**
 * an entity of cached project for my database
 *
 * the ID field is the primary key of this table
 *
 * ComlumgInfo names are from
 * @see ProjectConstants
 * they binds fields with sql requests
 */
@Entity(tableName = ProjectConstants.TABLE_NAME)
data class CachedProject (
    @PrimaryKey
    @ColumnInfo(name = ProjectConstants.COLUMN_PROJECT_ID)
    var id: String,
    var name: String,
    var fullName: String,
    var starCount: String,
    var dateCreated: String,
    var ownerName: String,
    var ownerAvatar: String,
    @ColumnInfo(name = ProjectConstants.COLUMN_IS_BOOKMARKED)
    var isBookmarked: Boolean
)