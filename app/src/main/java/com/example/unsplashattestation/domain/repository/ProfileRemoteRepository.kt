package com.example.unsplashattestation.domain.repository

import com.example.unsplashattestation.domain.model.Profile

interface ProfileRemoteRepository {

    suspend fun getProfile(): Profile
}