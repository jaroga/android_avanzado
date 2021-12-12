package com.keepcoding.imgram.ui.tvshowsdetail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.keepcoding.imgram.R
import com.keepcoding.imgram.databinding.TvshowDetailBinding
import com.keepcoding.imgram.ui.moviedetail.MovieReviewsAdapter
import com.keepcoding.imgram.ui.movies.MovieAdapter
import com.keepcoding.imgram.utils.RecyclerViewItemDecoration
import com.keepcoding.imgram.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowDetailActivity: AppCompatActivity() {
    private lateinit var binding: TvshowDetailBinding
    private val viewModel: TvShowDetailViewModel by viewModels()
    private val tvShowReviewsAdapter = TvShowReviewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TvshowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvshowReviews.adapter = tvShowReviewsAdapter
        binding.tvshowReviews.addItemDecoration(RecyclerViewItemDecoration(this, R.drawable.divider))
        binding.tvshowProgress.visible(true)

        val movieId = intent.getLongExtra("detallevista", 0)
        viewModel.getTvShowDetail(movieId.toInt())

        viewModel.images.observe(this) {
            binding.tvshowTitle.text = it.name
            binding.tvshowOverview.text = it.overview
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/${it.posterPath}")
                .placeholder(ContextCompat.getDrawable(this, R.mipmap.ic_launcher))
                .into(binding.tvshowImage)

            tvShowReviewsAdapter.addAll(it.reviews)
            binding.tvshowProgress.visible(false)
        }
    }
}