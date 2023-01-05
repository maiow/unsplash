package com.example.unsplashattestation.data.repository

import com.example.unsplashattestation.data.api.ApiProfile
import com.example.unsplashattestation.domain.repository.ProfileRemoteRepository
import com.example.unsplashattestation.domain.model.Profile
import javax.inject.Inject

class ProfileRemoteRepositoryImpl @Inject constructor(private val apiProfile: ApiProfile):
    ProfileRemoteRepository {

    override suspend fun getProfile(): Profile = apiProfile.getProfile().toProfile()
}