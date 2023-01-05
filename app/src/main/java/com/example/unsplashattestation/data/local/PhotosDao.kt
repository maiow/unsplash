package com.example.unsplashattestation.data.local

import androidx.paging.PagingSource
import androidx.room.*
import com.example.unsplashattestation.data.local.entity.PhotoEntity

@Dao
interface PhotosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photoEntity: List<PhotoEntity>)

    @Query("SELECT * FROM photos")
    fun getPhotos(): PagingSource<Int, PhotoEntity>

    @Query("DELETE FROM photos")
    suspend fun deleteAll()

    @Update
    suspend fun updateLocalLikes(photoEntity: PhotoEntity)

    @Transaction
    suspend fun refresh(data: List<PhotoEntity>){
        deleteAll()
        insert(data)
    }


}