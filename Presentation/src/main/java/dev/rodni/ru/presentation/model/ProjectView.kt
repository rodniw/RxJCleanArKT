package dev.rodni.ru.presentation.model

/**
 * project's model for the presentation layer
 */
data class ProjectView(val id: String, val name: String, val fullName: String,
                       val starCount: String, val dateCreated: String,
                       val ownerName: String, val ownerAvatar: String,
                       val isBookmarked: Boolean)
