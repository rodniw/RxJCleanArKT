package dev.rodni.ru.cache.factory

import dev.rodni.ru.cache.model.Config

/**
 * provides fake config objects
 */
object ConfigDataFactory {

    fun makeCachedConfig(): Config {
        return Config(DataFactory.randomInt(), DataFactory.randomLong())
    }

}