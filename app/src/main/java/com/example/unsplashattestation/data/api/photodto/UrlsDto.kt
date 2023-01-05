package com.example.unsplashattestation.data.api.photodto

import com.example.unsplashattestation.domain.model.Urls

data class UrlsDto(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val smallS3: String,
    val thumb: String
) {
    fun toPhotoDetailsUrls() = Urls(raw, regular)
}