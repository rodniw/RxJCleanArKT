package dev.rodni.ru.mobile_ui.model

/**
 * projct model for the ui layer
 */
data class Project(val id: String, val name: String, val fullName: String,
                   val starCount: String, val dateCreated: String,
                   val ownerName: String, val ownerAvatar: String,
                   val isBookmarked: Boolean)