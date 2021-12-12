package com.keepcoding.imgram.ui.tvshowsdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keepcoding.imgram.R
import com.keepcoding.imgram.databinding.ItemTvshowreviewBinding
import com.keepcoding.imgram.model.presentation.TvShowReviewsPresentation

class TvShowReviewsAdapter: RecyclerView.Adapter<TvShowReviewsAdapter.ImageViewHolder>() {

    var data = mutableListOf<TvShowReviewsPresentation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tvshowreview, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addAll(items: List<TvShowReviewsPresentation>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        private val binding = ItemTvshowreviewBinding.bind(itemView)
        private lateinit var item: TvShowReviewsPresentation

        fun bind(item: TvShowReviewsPresentation) {
            this.item = item
            with(binding) {
                tvshowReviewAuthor.text = "Autor: ${item.author}"
                tvshowReviewContent.text = item.content
            }
        }
    }
}