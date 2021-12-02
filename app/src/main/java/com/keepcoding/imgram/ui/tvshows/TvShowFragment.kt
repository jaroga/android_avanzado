package com.keepcoding.imgram.ui.tvshows

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.keepcoding.imgram.R
import com.keepcoding.imgram.databinding.FragmentMainBinding
import com.keepcoding.imgram.ui.TvShowAdapter
import com.keepcoding.imgram.ui.commons.viewBinding
import com.keepcoding.imgram.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel: TvShowViewModel by viewModels()
    private val imageAdapter = TvShowAdapter {
        viewModel.deleteTvShow(it)
    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_main, container, false)
//        return view
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            imageList.adapter = imageAdapter
            imageList.layoutManager = GridLayoutManager(context, 2)

            progress.visible(true)
        }

        viewModel.images.observe(this) {
            imageAdapter.addAll(it)
            binding.progress.visible(false)
        }
    }
}