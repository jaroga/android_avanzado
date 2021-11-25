package com.keepcoding.imgram.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keepcoding.imgram.R
import com.keepcoding.imgram.databinding.ItemListBinding
import com.keepcoding.imgram.model.Image
import com.keepcoding.imgram.model.TvShowPresentation

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var data = mutableListOf<TvShowPresentation>()

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

    fun addAll(items: List<TvShowPresentation>){
        data.addAll(items)
        notifyDataSetChanged()
    }

    class ImageViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        private val binding = ItemListBinding.bind(itemView)

        fun bind(item: TvShowPresentation) {

            with(binding) {
                imageTitle.text = item.name
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/${item.posterPath}")
                    .placeholder(ContextCompat.getDrawable(itemView.context, R.mipmap.ic_launcher))
                    .into(image)
            }
        }
    }
}