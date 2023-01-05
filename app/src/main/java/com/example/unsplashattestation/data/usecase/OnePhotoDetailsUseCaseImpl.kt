package com.example.unsplashattestation.data.usecase

import com.example.unsplashattestation.domain.repository.PhotoRemoteRepository
import com.example.unsplashattestation.domain.usecase.OnePhotoDetailsUseCase
import javax.inject.Inject

class OnePhotoDetailsUseCaseImpl @Inject constructor(private val repository: PhotoRemoteRepository) :
    OnePhotoDetailsUseCase {

    override suspend fun getPhotoDetails(id: String) =
        repository.getPhotoDetails(id).toPhotoDetails()

}