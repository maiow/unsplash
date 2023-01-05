package com.example.unsplashattestation.presentation.photos.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.unsplashattestation.data.state.ClickableView
import com.example.unsplashattestation.databinding.PhotoViewHolderBinding
import com.example.unsplashattestation.domain.model.Photo

class PhotoPagingAdapter(
    private val onClick: (ClickableView, Photo) -> Unit
) : PagingDataAdapter<Photo, PhotoViewHolder>(PhotoDiff()) {

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item) { ClickableView, Photo ->
                onClick(ClickableView, Photo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PhotoViewHolder(
        PhotoViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
}