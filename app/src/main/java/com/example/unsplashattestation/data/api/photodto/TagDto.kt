package com.example.unsplashattestation.data.api.photodto

import com.example.unsplashattestation.domain.model.Tags

data class TagDto(
    val title: String?
) {
    fun toPhotoDetailsTags() = Tags(title)
}