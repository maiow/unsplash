package com.example.unsplashattestation.data.api.photodto

import com.example.unsplashattestation.domain.model.Exif

data class ExifDto(
    val make: String?,
    val model: String?,
    val name: String?,
    val exposureTime: String?,
    val aperture: String?,
    val focalLength: String?,
    val iso: Int?
) {
    fun toPhotoDetailsExif() = Exif(make, model, model, exposureTime, aperture, focalLength, iso)
}