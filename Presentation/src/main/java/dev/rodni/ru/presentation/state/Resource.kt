package dev.rodni.ru.presentation.state

/**
 * resource holder
 */
data class Resource<out T> constructor(val status: ResourceState,
                                  val data: T?,
                                  val message: String?)