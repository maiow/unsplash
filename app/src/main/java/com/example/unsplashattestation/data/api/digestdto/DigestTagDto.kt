package com.example.unsplashattestation.data.api.digestdto

import com.example.unsplashattestation.domain.model.DigestTag

data class DigestTagDto(
    val title: String
) {

    fun toDigestTag() = DigestTag(title)
}
