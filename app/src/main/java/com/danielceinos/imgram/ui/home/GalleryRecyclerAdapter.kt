package com.danielceinos.imgram.ui.home

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danielceinos.imgram.databinding.GalleryItemLayoutBinding
import com.danielceinos.imgram.domain.image.Image
import com.danielceinos.imgram.utils.extensions.load
import com.google.android.material.chip.Chip


class GalleryRecyclerAdapter(
    val onDelete: (Image) -> Unit,
    val onShare: (Image) -> Unit
) : RecyclerView.Adapter<GalleryItemViewHolder>() {

    var images: List<Image> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var loggedAuthor: String? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var id: Int = 0
        get() {
            id = field + 1
            return field
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GalleryItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { GalleryItemViewHolder(this, id) }

    override fun onBindViewHolder(holder: GalleryItemViewHolder, position: Int) {
        holder.bind(
            image = images[position],
            position = position,
            onDelete = onDelete,
            onShare = onShare,
            loggedAuthor = loggedAuthor
        )
    }

    override fun getItemCount(): Int = images.count()
}

class GalleryItemViewHolder(
    private val binding: GalleryItemLayoutBinding,
    private val id: Int
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        image: Image,
        position: Int,
        loggedAuthor: String?,
        onDelete: (Image) -> Unit,
        onShare: (Image) -> Unit
    ) {

        with(binding) {
            galleryItemTitleText.text = image.title ?: "No title"
            galleryItemAuthorText.text = image.author
            galleryItemDebugText.text = "id = $id | pos = $position"
            galleryItemImageView.setImageBitmap(null)
            galleryItemAuthorAvatarImage.setImageBitmap(null)
            galleryItemLikesTextView.text = "${image.likes} Likes"

            galleryItemImageView.load(image.url)
            galleryItemAuthorAvatarImage.load(image.authorAvatar) {
                it.circleCrop()
            }

            galleryItemDeleteButton.visibility = when (image.canDelete(author = loggedAuthor)) {
                true -> View.VISIBLE
                false -> View.GONE
            }
            galleryItemDeleteButton.setOnClickListener { onDelete(image) }
            galleryItemShareButton.visibility = when (image.published) {
                true -> View.GONE
                false -> View.VISIBLE
            }
            galleryItemShareButton.setOnClickListener { onShare(image) }

            chipgroup.removeAllViewsInLayout()
            chipgroup.requestLayout()
            image.tags.forEach {
                val chip = Chip(binding.root.context)
                chip.text = it.name
                binding.chipgroup.addView(chip)
            }
        }
    }
}
