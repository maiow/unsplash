package com.example.unsplashattestation.domain.usecase

import com.example.unsplashattestation.domain.model.PhotoDetails

interface LikeDetailPhotoUseCase {

    suspend fun likeDetailPhoto(item: PhotoDetails)

}