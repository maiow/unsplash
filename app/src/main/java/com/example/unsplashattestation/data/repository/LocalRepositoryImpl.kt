package com.example.unsplashattestation.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.paging.PagingSource
import com.example.unsplashattestation.data.local.PhotosDao
import com.example.unsplashattestation.data.local.entity.PhotoEntity
import com.example.unsplashattestation.domain.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val photosDao: PhotosDao): LocalRepository {

    override suspend fun insertData(data: List<PhotoEntity>) = photosDao.insert(data)

    override fun getPagingData(): PagingSource<Int, PhotoEntity> = photosDao.getPhotos()

    override suspend fun clear() = photosDao.deleteAll()

    override suspend fun setLikeInDataBase(photoEntity: PhotoEntity) = photosDao.updateLocalLikes(photoEntity)

    override suspend fun refresh(data: List<PhotoEntity>) {
        Log.d(TAG, "refresh: ")
        photosDao.refresh(data)
    }
}