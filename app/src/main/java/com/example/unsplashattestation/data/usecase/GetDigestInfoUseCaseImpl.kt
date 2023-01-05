package com.example.unsplashattestation.data.usecase

import com.example.unsplashattestation.domain.repository.DigestRemoteRepository
import com.example.unsplashattestation.domain.usecase.GetDigestInfoUseCase
import javax.inject.Inject

class GetDigestInfoUseCaseImpl @Inject constructor(
    private val digestRemoteRepository: DigestRemoteRepository
    ): GetDigestInfoUseCase {

    override suspend fun getDigestInfo(id: String) = digestRemoteRepository.getDigestInfo(id)
}