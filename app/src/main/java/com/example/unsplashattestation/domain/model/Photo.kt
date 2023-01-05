package com.example.unsplashattestation.domain.model

import com.example.unsplashattestation.data.local.entity.PhotoEntity

data class Photo(
    val id: String,
    val urlsSmall: String,
    val likedByUser: Boolean,
    val likes: Int,
    var isLikeProgress: Boolean = false,
    val userName: String,
    val userAvatar: String,
    val height: Int,
    val width: Int
){
    fun toPhotoEntity() = PhotoEntity(
        photoId = id,
        smallUrls = urlsSmall,
        likedByUser = likedByUser,
        counterLikes = likes,
        userName = userName,
        profileImage = userAvatar
    )
}
