package com.example.unsplashattestation.presentation.photos.details

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.unsplashattestation.data.state.LoadState

import com.example.unsplashattestation.domain.model.PhotoDetails
import com.example.unsplashattestation.domain.usecase.LikeDetailPhotoUseCase
import com.example.unsplashattestation.domain.usecase.OnePhotoDetailsUseCase
import com.example.unsplashattestation.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class OnePhotoDetailsViewModel @Inject constructor(
    private val onePhotoDetailsUseCase: OnePhotoDetailsUseCase,
    private val likeDetailPhotoUseCase: LikeDetailPhotoUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow<DetailsState>(DetailsState.NotStartedYet)
    val state = _state.asStateFlow()

    var downloadID = 0L
    var downloading = true
    var success = false

    /**ну вот я открыл твой код не зна я что это за код
     * что такое а?
     * про стейты молчу уже и кстати...Все норм работает? лоадинг показывается?*/
    fun loadPhotoDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
                val a = onePhotoDetailsUseCase.getPhotoDetails(id = id)
                _loadState.value = LoadState.SUCCESS
                _state.value = DetailsState.Success(a)
        }
    }

    /**зачем тут два запроса?  про стейты молчу уже и кстати...Все норм работает? лоадинг показывается?
     * апишку если почитать то при получения лайка мы получаем точно такой же объект
     * не очень правильно тратить лишний трафик пользователь, ну и наши запросы...*/
    fun like(item: PhotoDetails) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            likeDetailPhotoUseCase.likeDetailPhoto(item)
            val a = onePhotoDetailsUseCase.getPhotoDetails(id = item.id)
            _loadState.value = LoadState.SUCCESS
            _state.value = DetailsState.Success(a)
        }
    }

    fun startDownLoad(url: String, downloadManager: DownloadManager) {
        val downloadRequest = DownloadManager.Request(Uri.parse(url))
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setTitle("Unsplash photo")
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                File.separator + "fromUnsplash.jpg"
            )
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        downloadID = downloadManager.enqueue(downloadRequest)
    }

    @SuppressLint("Range")
    fun getDMStatus(myDownloadManager: DownloadManager) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            val request = DownloadManager.Query().setFilterById(downloadID)
            var cursor: Cursor?
            request.setFilterByStatus(DownloadManager.STATUS_FAILED or DownloadManager.STATUS_SUCCESSFUL)
            while (downloading) {
                cursor = myDownloadManager.query(request)
                if (cursor.moveToPosition(0) && downloading) {
                    Log.i("Kart", "Downloading")
                    val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        downloading = false
                        success = true
                        cursor.close()
                    }
                    if (status == DownloadManager.STATUS_FAILED) {
                        downloading = false
                        success = false
                        cursor.close()
                    }
                } else {
                    cursor.close()
                }
            }
            Log.i("Kart", "Success = $success")
        }
    }
}