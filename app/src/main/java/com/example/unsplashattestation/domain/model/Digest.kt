package com.example.unsplashattestation.domain.model

data class Digest(
    val id: String,
    val title: String,
    val totalPhotos: Int,
    val userUsername: String,
    val userProfileImage: String,
    val previewPhoto: String,
    val description: String?,
    val tags: List<DigestTag>
)

data class DigestTag(
    val title: String
)


