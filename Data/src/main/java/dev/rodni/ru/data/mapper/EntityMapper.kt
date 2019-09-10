package dev.rodni.ru.data.mapper

//between layers data mapper
interface EntityMapper<E, D> {

    //this transform given entity to a pojo
    fun mapFromEntity(entity: E): D

    //from pojo to an entity
    fun mapToEntity(domain: D): E
}