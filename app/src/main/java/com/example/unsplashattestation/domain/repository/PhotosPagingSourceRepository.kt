package com.example.unsplashattestation.domain.repository

import androidx.paging.PagingData
import com.example.unsplashattestation.data.api.photodto.WrapperPhotoDto
import com.example.unsplashattestation.data.local.entity.PhotoEntity
import com.example.unsplashattestation.data.state.Requester
import com.example.unsplashattestation.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotosPagingSourceRepository {

    fun getFlowPhoto(requester: Requester): Flow<PagingData<Photo>>

    suspend fun setLike(id: String): WrapperPhotoDto

    suspend fun deleteLike(id: String): WrapperPhotoDto

    suspend fun updateLikeDB(entity: PhotoEntity)
}