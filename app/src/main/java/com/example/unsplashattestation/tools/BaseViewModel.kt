package com.example.unsplashattestation.tools

import androidx.lifecycle.ViewModel
import com.example.unsplashattestation.data.state.LoadState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel:ViewModel() {

    /**из AuthViewModel --- вот тут вообще лишнее. Для чего у нас в base view model  стоит стайт флоу а не шаред флоу?
     * вопервых если в большенство фрагментов лоад стейт старт то можно его прям стейте стартовым и
     * указать protected val _loadState =MutableStateFlow(LoadState.START) в байс вью моедл
     * во вторых _loadState.value = LoadState.START не требует корутиныпоэтому смотрите выше
     * без функции просто в одну строчку пишем нужный стейт, если он отлечается от базового 4 строчки
     * просто лишние*/
//    protected val _loadState =
//        MutableStateFlow(LoadState.LOADING)
    protected val _loadState = MutableStateFlow(LoadState.START)
    val loadState = _loadState.asStateFlow()

    protected val handler = CoroutineExceptionHandler { _, t ->
            _loadState.value = LoadState.ERROR
    }
}