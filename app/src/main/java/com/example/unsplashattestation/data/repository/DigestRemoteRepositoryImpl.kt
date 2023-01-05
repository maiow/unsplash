package com.example.unsplashattestation.data.repository

import com.example.unsplashattestation.data.api.ApiDigest
import com.example.unsplashattestation.domain.repository.DigestRemoteRepository
import com.example.unsplashattestation.domain.model.Digest
import com.example.unsplashattestation.tools.toListDigest
import javax.inject.Inject

class DigestRemoteRepositoryImpl @Inject constructor(private val apiDigest: ApiDigest) :
    DigestRemoteRepository {

    override suspend fun getDigests(page: Int): List<Digest> =
        apiDigest.getDigests(page).toListDigest()

    override suspend fun getDigestInfo(id: String): Digest = apiDigest.getDigestInfo(id).toDigest()

}