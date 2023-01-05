package com.example.unsplashattestation.data.usecase

import com.example.unsplashattestation.domain.repository.ProfileRemoteRepository
import com.example.unsplashattestation.domain.usecase.GetProfileUseCase
import javax.inject.Inject

class GetProfileUseCaseImpl @Inject constructor(
    private val profileRemoteRepository: ProfileRemoteRepository
    ) : GetProfileUseCase {

    override suspend fun getProfile() = profileRemoteRepository.getProfile()
}