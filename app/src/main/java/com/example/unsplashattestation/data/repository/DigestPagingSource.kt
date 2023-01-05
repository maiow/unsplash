package com.example.unsplashattestation.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.unsplashattestation.domain.model.Digest
import com.example.unsplashattestation.domain.repository.DigestRemoteRepository
import javax.inject.Inject

class DigestPagingSource @Inject constructor(private val repository: DigestRemoteRepository) :
    PagingSource<Int, Digest>() {


    override fun getRefreshKey(state: PagingState<Int, Digest>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Digest> {
        Log.d(TAG, "load: ")
        val page = params.key ?: FIRST_PAGE
        Log.i(TAG, "page: $page")
        return kotlin.runCatching {
            repository.getDigests(page)
        }.fold(
            onSuccess = {
                Log.i(TAG,"что-то грузит")
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page+1
                )

            }, onFailure = {
                Log.i(TAG,"не грузит нихрена")
                LoadResult.Error(it)
            }
        )
    }

    private companion object{
        private const val FIRST_PAGE = 1
    }
}
