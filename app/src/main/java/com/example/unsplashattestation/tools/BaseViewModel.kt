package com.example.unsplashattestation.tools

import androidx.lifecycle.ViewModel
import com.example.unsplashattestation.data.state.LoadState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel : ViewModel() {

    protected val _loadState = MutableStateFlow(LoadState.START)
    val loadState = _loadState.asStateFlow()

    protected val handler = CoroutineExceptionHandler { _, _ ->
        _loadState.value = LoadState.ERROR
    }
}