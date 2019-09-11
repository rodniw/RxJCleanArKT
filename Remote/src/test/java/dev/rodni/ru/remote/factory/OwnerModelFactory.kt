package dev.rodni.ru.remote.factory

import dev.rodni.ru.remote.model.OwnerModel

/**
 * my ProjectModelFactory needs to take Owner model object
 * ProjectModelFactory will do this with the help of this class
 */
object OwnerModelFactory {

    fun makeOwnerModel(): OwnerModel {
        return OwnerModel(DataFactory.randomUuid(), DataFactory.randomUuid())
    }
}