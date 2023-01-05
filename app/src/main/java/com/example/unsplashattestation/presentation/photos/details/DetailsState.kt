package com.example.unsplashattestation.presentation.photos.details

import com.example.unsplashattestation.domain.model.PhotoDetails

sealed class DetailsState {
    data class Success(val data: PhotoDetails): DetailsState()
    object NotStartedYet : DetailsState()
}
