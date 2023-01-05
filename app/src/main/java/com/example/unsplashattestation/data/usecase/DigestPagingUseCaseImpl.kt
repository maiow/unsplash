package com.example.unsplashattestation.data.usecase

import android.content.ContentValues.TAG
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.unsplashattestation.data.repository.DigestPagingSource
import com.example.unsplashattestation.domain.model.Digest
import com.example.unsplashattestation.domain.repository.DigestRemoteRepository
import com.example.unsplashattestation.domain.usecase.DigestPagingUseCase
import javax.inject.Inject

class DigestPagingUseCaseImpl @Inject constructor(
    private val repository: DigestRemoteRepository
): DigestPagingUseCase {
    /**если прикручивать рефрешер к пейджинг сурсу, то при обновлени приложение вылетает сошибкой
     * вычитал, что в пейджере должен каждый раз создаваться новый экземпляр сурса
     * так что вот**/
    override fun getDigest():Pager<Int, Digest> {
        Log.d(TAG, "getDigest: ")
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { DigestPagingSource(repository) }
        )
    }
}