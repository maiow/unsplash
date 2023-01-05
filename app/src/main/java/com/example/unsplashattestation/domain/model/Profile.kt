package com.example.unsplashattestation.domain.model

data class Profile(
    val id: String,
    val userName: String,
    val name: String?,
    val location: String?,
    val avatar: String,
    val totalLikes: Int
)
