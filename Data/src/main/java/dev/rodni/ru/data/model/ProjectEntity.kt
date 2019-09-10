package dev.rodni.ru.data.model

//entity of the github's project
class ProjectEntity(val id: String, val name: String, val fullName: String,
                    val starCount: String, val dateCreated: String,
                    val ownerName: String, val ownerAvatar: String,
                    val isBookmarked: Boolean)