package com.example.unsplashattestation.domain.usecase

import com.example.unsplashattestation.domain.model.Profile

interface GetProfileUseCase {

    suspend fun getProfile(): Profile
}