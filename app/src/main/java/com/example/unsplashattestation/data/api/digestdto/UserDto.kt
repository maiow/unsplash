package com.example.unsplashattestation.data.api.digestdto


import com.google.gson.annotations.SerializedName

data class UserDto(
    val id: String,
    val username: String,
    val name: String,
    @SerializedName("profile_image")
    val profileImage: ProfileImageDto
)