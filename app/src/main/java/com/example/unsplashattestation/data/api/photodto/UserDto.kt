package com.example.unsplashattestation.data.api.photodto

import com.example.unsplashattestation.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserDto(
    val bio: String?,
    val name: String,
    @SerializedName("profile_image")
    val profileImage: ProfileImageDto,
    val username: String
) {
    fun toPhotoDetailsUser() = User(bio, name, profileImage.toPhotoDetailsProfileImage(), username)
}