package com.example.unsplashattestation.tools

import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.unsplashattestation.R
import com.example.unsplashattestation.data.api.digestdto.DigestDto
import com.example.unsplashattestation.data.api.digestdto.DigestTagDto
import com.example.unsplashattestation.data.api.photodto.PhotoDto
import com.example.unsplashattestation.data.api.photodto.TagDto
import com.example.unsplashattestation.data.local.entity.PhotoEntity
import com.example.unsplashattestation.domain.model.Digest
import com.example.unsplashattestation.domain.model.DigestTag
import com.example.unsplashattestation.domain.model.Photo
import com.example.unsplashattestation.domain.model.Tags

fun List<PhotoDto>.toListPhoto(): List<Photo> {
    val newList = mutableListOf<Photo>()

    this.forEach { item ->
        newList.add(item.toPhoto())
    }
    return newList
}

fun List<TagDto>.toListTag(): List<Tags> {
    return this.map { item -> item.toPhotoDetailsTags() }
}

fun List<DigestDto>.toListDigest(): List<Digest> {
    val newList = mutableListOf<Digest>()

    this.forEach { item ->
        newList.add(item.toDigest())
    }
    return newList
}

fun List<DigestTagDto>.toListDigestTag(): List<DigestTag> {
    return this.map { item -> item.toDigestTag() }
}

fun List<PhotoDto>.toListPhotoEntity(): List<PhotoEntity> {
    return this.map {
        it.toPhotoEntity()
    }
}

fun ImageView.loadImage(urls: String) {
    Glide.with(this)
        .load(urls)
        .error(R.drawable.error_image)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.placeholder)
        .into(this)
}

fun ImageView.loadImage(imageId: Int) {
    Glide.with(this)
        .load(imageId)
        .error(R.drawable.error_image)
        .placeholder(R.drawable.placeholder)
        .into(this)
}

fun SearchView.setChangeTextListener(block: (query: String) -> Unit) {

    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

        override fun onQueryTextChange(newText: String): Boolean {
            block(newText)
            return false
        }

        override fun onQueryTextSubmit(query: String): Boolean {
            return false
        }
    })
}
