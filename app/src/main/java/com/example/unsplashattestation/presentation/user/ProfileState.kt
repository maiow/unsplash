package com.example.unsplashattestation.presentation.user

import com.example.unsplashattestation.domain.model.Profile

/**не тут должен лежать*/

sealed class ProfileState{
    data class Success(val data: Profile): ProfileState()
    object NotStartedYet : ProfileState()
}
