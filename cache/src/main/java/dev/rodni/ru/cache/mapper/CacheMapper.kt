package dev.rodni.ru.cache.mapper

/**
 * object mapper to send pojo to the data layer and receive from the data layer
 */
interface CacheMapper<C, E> {

    /**
     * takes in a cache and gives to the data layer an entity
     */
    fun mapFromCached(type: C): E

    /**
     * reverse from the previous function
     */
    fun mapToCached(type: E): C
}