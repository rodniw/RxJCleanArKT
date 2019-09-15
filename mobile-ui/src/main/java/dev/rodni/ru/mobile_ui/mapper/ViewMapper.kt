package dev.rodni.ru.mobile_ui.mapper

/**
 * abstract mapper for this layer
 */
interface ViewMapper<P, V> {

    fun mapToView(presentation: P): V
}