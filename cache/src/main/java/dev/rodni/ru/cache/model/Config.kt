package dev.rodni.ru.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.rodni.ru.cache.db.ConfigConstants

/**
 * Config table's model
 */
@Entity(tableName = ConfigConstants.TABLE_NAME)
data class Config(
    @PrimaryKey(autoGenerate = true)
    var id: Int = -1,
    var lastCacheTime: Long)