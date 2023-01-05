package com.example.unsplashattestation.presentation.collections.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.unsplashattestation.domain.model.Digest
import com.example.unsplashattestation.databinding.DigestViewHolderBinding

class DigestPagingAdapter(
    private val onClick: (Digest) -> Unit,
) : PagingDataAdapter<Digest, DigestViewHolder>(DigestDiff()) {

    override fun onBindViewHolder(holder: DigestViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item) { digest -> onClick(digest) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DigestViewHolder(
        DigestViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
}