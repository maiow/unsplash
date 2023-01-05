package com.example.unsplashattestation.domain.usecase

import androidx.paging.Pager
import com.example.unsplashattestation.domain.model.Digest

interface DigestPagingUseCase {

    fun getDigest(): Pager<Int, Digest>
}