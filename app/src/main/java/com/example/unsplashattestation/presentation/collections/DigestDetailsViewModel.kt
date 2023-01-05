package com.example.unsplashattestation.presentation.collections

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.unsplashattestation.data.state.LoadState
import com.example.unsplashattestation.data.state.Requester
import com.example.unsplashattestation.domain.model.Photo
import com.example.unsplashattestation.domain.usecase.GetDigestInfoUseCase
import com.example.unsplashattestation.domain.usecase.PhotoLikeUseCase
import com.example.unsplashattestation.domain.usecase.PhotosPagingUseCase
import com.example.unsplashattestation.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

/** вот именно из такого большого конструктора я не вижу смысла в использовавнии юзкейсов
 * но тут может я не прав*/

@HiltViewModel
class DigestDetailsViewModel @Inject constructor(
    private val photosPagingUseCase: PhotosPagingUseCase,
    private val photoLikeUseCase: PhotoLikeUseCase,
    private val getDigestInfoUseCase: GetDigestInfoUseCase,
) : BaseViewModel() {

    /**это же детальный фрагмент конкретной коллекции? зачем тебе тут фолу айдишников?
     * ну я как бынима что ты сделал по образу и подобую...но блять это так глупо тут выглядит*/
    private val id = MutableStateFlow("")
    private var job: Job? = null

    private val _state = MutableStateFlow<DigestState>(DigestState.NotStartedYet)
    val state = _state.asStateFlow()

    fun getDigestInfo(id: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            val a = getDigestInfoUseCase.getDigestInfo(id = id)
            _loadState.value = LoadState.SUCCESS
            /** это вообще зачем?
             * у нас есть стейт состояний экрана, оставалось завести стей с данными, из за этого каша
             * во фрагменте...и название переменной "a" не должно быть в финальной версии проекта*/
            _state.value = DigestState.Success(a)
        }
    }

    /**флоу тут лишний
     * ну тоесть у тебя фрагмент за раз не выдает больше одной коллекции? так зачем систему нагружать потоком?*/
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getPhoto() = id.asStateFlow()
        .flatMapLatest { photosPagingUseCase.getPhoto(Requester.COLLECTIONS.apply { param = it }) }
        .cachedIn(CoroutineScope(Dispatchers.IO))


    /** зачем тут стей сексаес еще? */
    fun like(item: Photo) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            photoLikeUseCase.likePhoto(item)
            _loadState.value = LoadState.SUCCESS
        }
    }

    /** Сережа наверное ты делал...я понимаю что ты переписал кусок кода с поиска, но ты хотя бы разобрался
     *  в нем...это тут абсолютно лишнее...это будет работать..но это слишком сложно*/
    fun setId(newText: String, refresh: () -> Unit) {
        job?.cancel()
        job = viewModelScope.launch {
            id.value = newText
            refresh()
        }
    }
}
