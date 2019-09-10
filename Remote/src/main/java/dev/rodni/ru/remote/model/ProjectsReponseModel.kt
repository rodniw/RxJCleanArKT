package dev.rodni.ru.remote.model

/**
 * model for a request from the api
 * api will send to user list of projects
 */
data class ProjectsReponseModel(val items: List<ProjectModel>)