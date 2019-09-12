package dev.rodni.ru.presentation.mapper

/**
 * base mapper for the presentation layer
 */
interface Mapper<out V, in D> {

    /**
     * convert incoming object to a model of the presentation layer
     */
    fun mapToView(type: D): V
}