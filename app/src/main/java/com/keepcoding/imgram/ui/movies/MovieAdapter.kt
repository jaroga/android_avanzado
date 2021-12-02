package com.keepcoding.imgram.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keepcoding.imgram.R
import com.keepcoding.imgram.databinding.ItemListBinding
import com.keepcoding.imgram.databinding.ItemMovieBinding
import com.keepcoding.imgram.model.presentation.MoviePresentation

class MovieAdapter(
    private val clickListener: (MoviePresentation) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ImageViewHolder>() {

    var data = mutableListOf<MoviePresentation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addAll(items: List<MoviePresentation>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        private val binding = ItemMovieBinding.bind(itemView)
        private lateinit var item: MoviePresentation

        init {
            binding.image.setOnClickListener {
                clickListener(item)
            }
        }

        fun bind(item: MoviePresentation) {
            this.item = item
            with(binding) {
                imageTitle.text = item.title
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/${item.posterPath}")
                    .placeholder(ContextCompat.getDrawable(itemView.context, R.mipmap.ic_launcher))
                    .into(image)

                popularity.text = "%.2f".format(item.popularity)
            }
        }
    }
}