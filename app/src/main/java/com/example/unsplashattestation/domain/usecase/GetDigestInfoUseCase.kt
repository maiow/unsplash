package com.example.unsplashattestation.domain.usecase

import com.example.unsplashattestation.domain.model.Digest

interface GetDigestInfoUseCase {

    suspend fun getDigestInfo(id: String): Digest
}