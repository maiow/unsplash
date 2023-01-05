package com.example.unsplashattestation.domain.usecase

import com.example.unsplashattestation.domain.model.PhotoDetails

interface OnePhotoDetailsUseCase {

    suspend fun getPhotoDetails(id: String): PhotoDetails

}