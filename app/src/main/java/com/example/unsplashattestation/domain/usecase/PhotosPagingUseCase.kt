package com.example.unsplashattestation.domain.usecase

import androidx.paging.PagingData
import com.example.unsplashattestation.data.state.Requester
import com.example.unsplashattestation.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotosPagingUseCase {

    fun getPhoto(requester: Requester) : Flow<PagingData<Photo>>

}
