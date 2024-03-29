package dev.rodni.ru.remote.model

import com.google.gson.annotations.SerializedName

/**
 * model data class for project item
 */
data class ProjectModel(val id: String,
                        val name: String,
                        @SerializedName("full_name") val fullName: String,
                        @SerializedName("stargazers_count") val starCount: Int,
                        @SerializedName("created_at") val dateCreated: String,
                        val owner: OwnerModel) {
}