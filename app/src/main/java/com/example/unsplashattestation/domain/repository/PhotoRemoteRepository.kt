package com.example.unsplashattestation.domain.repository

import com.example.unsplashattestation.data.api.photodto.PhotoDetailsDto
import com.example.unsplashattestation.data.api.photodto.PhotoListDto
import com.example.unsplashattestation.data.api.photodto.WrapperPhotoDto
import com.example.unsplashattestation.data.state.Requester

interface PhotoRemoteRepository {

    suspend fun getPhotoList(requester: Requester, page: Int): PhotoListDto

    suspend fun getPhotoDetails(id: String): PhotoDetailsDto

    suspend fun likePhoto(id: String): WrapperPhotoDto

    suspend fun unlikePhoto(id: String): WrapperPhotoDto
}