package com.example.unsplashattestation.data.api.photodto

import com.example.unsplashattestation.domain.model.ProfileImage

data class ProfileImageDto(
    val large: String,
    val medium: String,
    val small: String
) {
    fun toPhotoDetailsProfileImage() = ProfileImage(small)
}