package dev.rodni.ru.remote.model

import com.google.gson.annotations.SerializedName

/**
 * model data class for project's owner
 */
data class OwnerModel(@SerializedName("login") val ownerName: String,
                 @SerializedName("avatar_url") val ownerAvatar: String) {
}