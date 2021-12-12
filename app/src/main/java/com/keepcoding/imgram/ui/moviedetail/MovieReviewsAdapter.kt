package com.keepcoding.imgram.ui.moviedetail

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keepcoding.imgram.R
import com.keepcoding.imgram.databinding.ItemMoviereviewBinding
import com.keepcoding.imgram.model.presentation.MovieReviewsPresentation

class MovieReviewsAdapter: RecyclerView.Adapter<MovieReviewsAdapter.ImageViewHolder>() {

    var data = mutableListOf<MovieReviewsPresentation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_moviereview, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addAll(items: List<MovieReviewsPresentation>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        private val binding = ItemMoviereviewBinding.bind(itemView)
        private lateinit var item: MovieReviewsPresentation

        fun bind(item: MovieReviewsPresentation) {
            this.item = item
            with(binding) {
                reviewAuthor.text = "Autor: ${item.author}"
                reviewContent.text = item.content
            }
        }
    }
}