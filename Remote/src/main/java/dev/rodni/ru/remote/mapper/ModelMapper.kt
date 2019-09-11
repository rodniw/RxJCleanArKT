package dev.rodni.ru.remote.mapper

/**
 * mapper for models which passing into entities of the data layer
 *
 */
interface ModelMapper<in M, out E> {

    /**
     * map from model to an entity
     */
    fun mapFromModel(model: M): E

}