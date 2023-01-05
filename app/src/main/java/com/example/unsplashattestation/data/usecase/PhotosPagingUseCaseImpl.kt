package com.example.unsplashattestation.data.usecase

import com.example.unsplashattestation.data.state.Requester
import com.example.unsplashattestation.domain.repository.PhotosPagingSourceRepository
import com.example.unsplashattestation.domain.usecase.PhotosPagingUseCase
import javax.inject.Inject

class PhotosPagingUseCaseImpl @Inject constructor(
    private val photosRepository: PhotosPagingSourceRepository
): PhotosPagingUseCase {
    override fun getPhoto(requester: Requester)=
        photosRepository.getFlowPhoto(requester)

}
