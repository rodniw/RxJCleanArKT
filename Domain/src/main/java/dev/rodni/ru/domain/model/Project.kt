package dev.rodni.ru.domain.model

//my model for projects in trending repos
//isBookmarked is to know saved project or not
class Project(val id: String, val name: String, val fullName: String,
              val starCount: String, val dateCreated: String,
              val ownerName: String, val ownerAvatar: String,
              val isBookmarked: Boolean)