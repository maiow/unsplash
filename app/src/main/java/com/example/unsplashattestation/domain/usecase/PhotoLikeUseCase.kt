package com.example.unsplashattestation.domain.usecase

import com.example.unsplashattestation.domain.model.Photo

interface PhotoLikeUseCase {

    suspend fun likePhoto(item: Photo)
}