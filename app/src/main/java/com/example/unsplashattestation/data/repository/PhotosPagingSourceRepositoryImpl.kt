package com.example.unsplashattestation.data.repository

import androidx.paging.*
import com.example.unsplashattestation.data.api.photodto.WrapperPhotoDto
import com.example.unsplashattestation.data.local.entity.PhotoEntity
import com.example.unsplashattestation.data.state.Requester
import com.example.unsplashattestation.domain.model.Photo
import com.example.unsplashattestation.domain.repository.LocalRepository
import com.example.unsplashattestation.domain.repository.PhotoRemoteRepository
import com.example.unsplashattestation.domain.repository.PhotosPagingSourceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PhotosPagingSourceRepositoryImpl @Inject constructor(
    private val photoRemoteRepository: PhotoRemoteRepository,
    private val localRepository: LocalRepository
): PhotosPagingSourceRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getFlowPhoto(requester: Requester): Flow<PagingData<Photo>>
        = Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            remoteMediator = PhotosRemoteMediator(localRepository, photoRemoteRepository, requester),
            pagingSourceFactory = { localRepository.getPagingData() }
        ).flow.map {
            it.map { entity ->
                entity.toPhoto()
            }
        }

    override suspend fun setLike(id: String): WrapperPhotoDto = photoRemoteRepository.likePhoto(id)

    override suspend fun deleteLike(id: String): WrapperPhotoDto = photoRemoteRepository.unlikePhoto(id)

    override suspend fun updateLikeDB(entity: PhotoEntity) = localRepository.setLikeInDataBase(entity)
}