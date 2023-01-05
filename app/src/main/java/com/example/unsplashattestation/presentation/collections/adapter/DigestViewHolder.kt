package com.example.unsplashattestation.presentation.collections.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashattestation.R
import com.example.unsplashattestation.domain.model.Digest
import com.example.unsplashattestation.databinding.DigestViewHolderBinding
import com.example.unsplashattestation.tools.loadImage

class DigestViewHolder(private val binding: DigestViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Digest, onClick: (item: Digest) -> Unit) {

        binding.root.setOnClickListener {
            onClick(item)
        }

        binding.preview.loadImage(item.previewPhoto)
        binding.authorAvatar.loadImage(item.userProfileImage)
        binding.authorName.text = item.userUsername
        binding.totalPhotos.text =
            itemView.context.resources.getQuantityString(
                R.plurals.total_photos, item.totalPhotos, item.totalPhotos
            )
        binding.collectionTitle.text = item.title

    }
}

