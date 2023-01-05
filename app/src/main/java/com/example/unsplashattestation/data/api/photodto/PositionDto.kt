package com.example.unsplashattestation.data.api.photodto

import com.example.unsplashattestation.domain.model.Position

data class PositionDto(
    val latitude: Double?,
    val longitude: Double?
) {
    fun toPhotoDetailsPosition() = Position(latitude, longitude)
}