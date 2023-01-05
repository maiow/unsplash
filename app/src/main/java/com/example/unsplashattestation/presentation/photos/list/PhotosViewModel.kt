package com.example.unsplashattestation.presentation.photos.list

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.unsplashattestation.data.state.LoadState
import com.example.unsplashattestation.data.state.Requester
import com.example.unsplashattestation.domain.model.Photo
import com.example.unsplashattestation.domain.usecase.PhotoLikeUseCase
import com.example.unsplashattestation.domain.usecase.PhotosPagingUseCase
import com.example.unsplashattestation.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val photosPagingUseCase: PhotosPagingUseCase,
    private val photoLikeUseCase: PhotoLikeUseCase
) : BaseViewModel() {

    private val query = MutableStateFlow("")
    private var job: Job? = null

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getPhoto() = query.asStateFlow()
        .flatMapLatest { photosPagingUseCase.getPhoto(Requester.ALL_LIST.apply { param = it }) }
        .cachedIn(CoroutineScope(Dispatchers.IO))


    fun like(item: Photo) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            photoLikeUseCase.likePhoto(item)
            _loadState.value = LoadState.SUCCESS
        }
    }

    fun setQuery(newText: String, refresh: () -> Unit) {
        job?.cancel()
        job = viewModelScope.launch {
            delay(500)
            query.value = newText
            refresh()
        }
    }
}