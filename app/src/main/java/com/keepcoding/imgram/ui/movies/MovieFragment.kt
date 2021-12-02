package com.keepcoding.imgram.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.keepcoding.imgram.R
import com.keepcoding.imgram.databinding.FragmentMainBinding
import com.keepcoding.imgram.ui.commons.viewBinding
import com.keepcoding.imgram.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel: MovieViewModel by viewModels()
    private val movieAdapter = MovieAdapter {

    }

    private var id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = arguments?.getLong("id")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            imageList.adapter = movieAdapter
            imageList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            progress.visible(true)
        }

        viewModel.images.observe(this) {
            movieAdapter.addAll(it)
            binding.progress.visible(false)
        }
    }
}