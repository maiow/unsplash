package com.example.unsplashattestation.presentation.photos.details

import com.example.unsplashattestation.domain.model.PhotoDetails

/**опять салед класс и опять не там лежит где должен*/
sealed class DetailsState {
    data class Success(val data: PhotoDetails): DetailsState()
    object NotStartedYet : DetailsState()
}
