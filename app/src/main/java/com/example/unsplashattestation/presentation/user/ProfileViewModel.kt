package com.example.unsplashattestation.presentation.user

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.unsplashattestation.data.state.LoadState
import com.example.unsplashattestation.data.state.Requester
import com.example.unsplashattestation.domain.model.Photo
import com.example.unsplashattestation.domain.model.Profile
import com.example.unsplashattestation.domain.usecase.GetProfileUseCase
import com.example.unsplashattestation.domain.usecase.PhotoLikeUseCase
import com.example.unsplashattestation.domain.usecase.PhotosPagingUseCase
import com.example.unsplashattestation.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val photosPagingUseCase: PhotosPagingUseCase,
    private val photoLikeUseCase: PhotoLikeUseCase
) : BaseViewModel() {

    /**флоу и джоба лишниее*/
    private val userName = MutableStateFlow("")
    private var job: Job? = null

    private val _profile = MutableSharedFlow<Profile>()
    val profile = _profile.asSharedFlow()

    private val _state = MutableStateFlow<ProfileState>(ProfileState.NotStartedYet)
    val state = _state.asStateFlow()

    /**что такое а?*/
    fun getProfile() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            val a = getProfileUseCase.getProfile()
            _loadState.value = LoadState.SUCCESS
            _state.value = ProfileState.Success(a)
        }
    }

    /**перемудрил...писал в другом классе почему*/
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getPhoto() = userName.asStateFlow()
        .flatMapLatest { photosPagingUseCase.getPhoto(Requester.PROFILE.apply { param = it })}
        .cachedIn(CoroutineScope(Dispatchers.IO))

    /**зачем тут сексес?*/
    fun like(item: Photo) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            photoLikeUseCase.likePhoto(item)
            _loadState.value = LoadState.SUCCESS
        }
    }

    /**перемудрил*/
    fun setUsername(newText: String, refresh: () -> Unit) {
        job?.cancel()
        job = viewModelScope.launch {
            userName.value = newText
            refresh()
        }
    }
}