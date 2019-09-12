package dev.rodni.ru.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.rodni.ru.cache.db.ConfigConstants
import dev.rodni.ru.cache.model.Config
import io.reactivex.Flowable

/**
 * this dao for interacting with a data of a last cache time
 */
@Dao
abstract class ConfigDao {

    /**
     * selecting config from the config's table
     */
    @Query(ConfigConstants.QUERY_CONFIG)
    abstract fun getConfig(): Flowable<Config>

    /**
     * updates config's table
     *
     * replaces an old item with a new item
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertConfig(config: Config)
}