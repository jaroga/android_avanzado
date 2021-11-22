package com.keepcoding.imgram.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keepcoding.imgram.R
import com.keepcoding.imgram.databinding.ItemListBinding
import com.keepcoding.imgram.model.Image

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    var data = mutableListOf<Image>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        for (i in 0..100) {
            data.add(Image(i.toString(), i.toString(), i.toString()))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ImageViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        private val binding = ItemListBinding.bind(itemView)

        fun bind(item: Image) {

            with(binding) {
                imageTitle.text = item.toString()
                Glide.with(itemView.context)
                    .load(item.url)
                    .placeholder(ContextCompat.getDrawable(itemView.context, R.mipmap.ic_launcher))
                    .into(image)
            }
        }
    }
}