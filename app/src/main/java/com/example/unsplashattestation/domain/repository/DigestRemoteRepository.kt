package com.example.unsplashattestation.domain.repository

import com.example.unsplashattestation.domain.model.Digest

interface DigestRemoteRepository {

    suspend fun getDigests(page: Int): List<Digest>

    suspend fun getDigestInfo(id: String): Digest
}