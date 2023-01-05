package com.example.unsplashattestation.presentation.collections

import com.example.unsplashattestation.domain.usecase.DigestPagingUseCase
import com.example.unsplashattestation.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DigestViewModel @Inject constructor(
    private val digestPagingUseCase: DigestPagingUseCase
    ) : BaseViewModel() {

    fun getDigest() = digestPagingUseCase.getDigest().flow
}