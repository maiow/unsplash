package com.example.unsplashattestation.presentation.collections

import com.example.unsplashattestation.domain.model.Digest

/** это явно не в призентатион слое лежать должно */

sealed class DigestState {
    data class Success(val data: Digest) : DigestState()
    object NotStartedYet : DigestState()
}
