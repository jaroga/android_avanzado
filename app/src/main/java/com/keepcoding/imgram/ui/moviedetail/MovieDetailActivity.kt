package com.keepcoding.imgram.ui.moviedetail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.keepcoding.imgram.R
import com.keepcoding.imgram.databinding.MovieDetailBinding
import com.keepcoding.imgram.utils.RecyclerViewItemDecoration
import com.keepcoding.imgram.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity: AppCompatActivity() {
    private lateinit var binding: MovieDetailBinding
    private val viewModel: MovieDetailViewModel by viewModels()
    private val movieReviewsAdapter = MovieReviewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.movieReviews.adapter = movieReviewsAdapter
        binding.movieReviews.addItemDecoration(RecyclerViewItemDecoration(this, R.drawable.divider))

        binding.movieProgress.visible(true)

        val movieId = intent.getLongExtra("detallevista", 0)
        viewModel.getMovieDetail(movieId.toInt())

        viewModel.images.observe(this) {
            binding.movieTitle.text = it.title
            binding.movieOverview.text = it.overview
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/${it.posterPath}")
                .placeholder(ContextCompat.getDrawable(this, R.mipmap.ic_launcher))
                .into(binding.movieImage)

            movieReviewsAdapter.addAll(it.reviews)

            binding.movieProgress.visible(false)
        }
    }
}