package com.example.unsplashattestation.data.api.photodto

import com.example.unsplashattestation.data.local.entity.PhotoEntity

class PhotoListDto : ArrayList<PhotoDto>(){

    fun toListEntity(): MutableList<PhotoEntity> {
        val newList = mutableListOf<PhotoEntity>()
        this.forEach {
            newList.add(it.toPhotoEntity())
        }
        return newList
    }
}