package com.example.unsplashattestation.data.usecase

import com.example.unsplashattestation.domain.model.PhotoDetails
import com.example.unsplashattestation.domain.repository.PhotosPagingSourceRepository
import com.example.unsplashattestation.domain.usecase.LikeDetailPhotoUseCase
import javax.inject.Inject

class LikeDetailPhotoUseCaseImpl @Inject constructor(private val repository: PhotosPagingSourceRepository) :
    LikeDetailPhotoUseCase {

    override suspend fun likeDetailPhoto(item: PhotoDetails) {
        if (item.likedByUser) repository.deleteLike(item.id).photo
        else repository.setLike(item.id).photo
    }
}