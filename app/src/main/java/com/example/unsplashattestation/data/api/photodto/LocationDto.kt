package com.example.unsplashattestation.data.api.photodto

import com.example.unsplashattestation.domain.model.Location

data class LocationDto(
    val city: String?,
    val country: String?,
    val position: PositionDto
) {
    fun toPhotoDetailsLocation() = Location(city, position.toPhotoDetailsPosition())
}