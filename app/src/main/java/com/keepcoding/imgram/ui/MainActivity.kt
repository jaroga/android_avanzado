package com.keepcoding.imgram.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.google.android.material.navigation.NavigationBarView
import com.keepcoding.imgram.R
import com.keepcoding.imgram.ThreadManager
import com.keepcoding.imgram.databinding.ActivityMainBinding
import com.keepcoding.imgram.managers.MainManager
import com.keepcoding.imgram.model.Image
import com.keepcoding.imgram.ui.favourites.FavouritesFragment
import com.keepcoding.imgram.ui.movies.MovieAdapter
import com.keepcoding.imgram.ui.movies.MovieFragment
import com.keepcoding.imgram.ui.tvshows.TvShowFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var imageAdapter = MovieAdapter(clickListener = {
//        viewModel.deleteTvShow(it)
    })

//    private val viewModel: MainViewModel by lazy {
//        ViewModelProvider(this).get(MainViewModel::class.java)
//    }

//    private val viewModel: MainViewModel by viewModels()

    //private lateinit var lateinitViewModel: MainViewModel

    private val mainManager = MainManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val intent = Intent(this, MainActivity::class.java)
//        intent.putExtra("HOLA", "H")
//        startActivity(intent)

//        supportFragmentManager.beginTransaction()
//            .add(binding.fragmentContainer1.id, MainFragment::class.java, bundleOf())
//            .commit()
//        supportFragmentManager.beginTransaction()
//            .add(binding.fragmentContainer2.id, MainFragment::class.java, bundleOf())
//            .commit()
//        supportFragmentManager.beginTransaction()
//            .add(binding.fragmentContainer3.id, MainFragment::class.java, bundleOf())
//            .commit()
//        supportFragmentManager.beginTransaction()
//            .add(binding.fragmentContainer4.id, MainFragment::class.java, bundleOf())
//            .commit()

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, TvShowFragment::class.java, bundleOf(Pair("key", "value")))
//            .replace(binding.fragmentContainer.id, TvShowFragment::class.java, bundleOf("key" to "value"))
            .commit()




        with(binding){
//            imageList.adapter = imageAdapter
//            imageList.layoutManager =
//                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

//            imageList.layoutManager = GridLayoutManager(this@MainActivity, 2)

            // Error de lateinit no inicializado
            //lateinitViewModel.getHola()

            button.setOnClickListener {
//                viewModel.deleteAllTvShow()
//                viewModel.deleteTvShow(imageAdapter.data[0])

//                viewModel.lanzarThread{
//                    Log.d("MainActivity", "Final count: $it")
//                }
//                mainManager.diTuVariable(
//                viewModel.getTvShows()
            }

            bottomNavigationView.setOnItemSelectedListener(object: NavigationBarView.OnItemSelectedListener {
                override fun onNavigationItemSelected(item: MenuItem): Boolean {

                    when(item.itemId){
                        R.id.menu_tv_shows -> {
//                            Log.d("ActivityMain", "Menu top clicked")
                            supportFragmentManager.beginTransaction()
                                .replace(binding.fragmentContainer.id, TvShowFragment::class.java, bundleOf())
                                .commit()
                        }
                        R.id.menu_movies -> {
//                            Log.d("ActivityMain", "Menu hot clicked")
                            supportFragmentManager.beginTransaction()
                                .replace(binding.fragmentContainer.id, MovieFragment::class.java, bundleOf())
                                .commit()
                        }
                        R.id.menu_favourites -> {
//                            Log.d("ActivityMain", "Menu hot clicked")
                            supportFragmentManager.beginTransaction()
                                .replace(binding.fragmentContainer.id, FavouritesFragment::class.java, bundleOf())
                                .commit()
                        }
                        else -> {
                            Log.d("ActivityMain", "No deberías estar aqui")
                        }
                    }

                    return false
                }

            })

            // Inicialización del lateinit
            //lateinitViewModel = ViewModelProvider(this@MainActivity).get(MainViewModel::class.java)
        }

        val images = mutableListOf<Image>()

        for (i in 0..20){
            images.add(Image(i.toString(), "Title $i", "https://picsum.photos/200/300"))
        }

//        Thread.sleep(100000)

//        binding.progress.visible(true)

//        viewModel.images.observe(this){
//            imageAdapter.addAll(it)
//            binding.progress.visible(false)
//        }
    }

    override fun onPause() {
//        viewModel.pararCoroutines()
        super.onPause()
    }

    fun crearThread(){
        Thread().run {
            for (i in 0..100){
                Thread.sleep(100)
                Log.d("HOLA", "HOLA")
            }
        }
    }

    fun crearCallback(){
        val threadManager = ThreadManager()

        Log.d("HOLA", "ADIOS")
        threadManager.calcSum() {

            Log.d("HOLA", "$it")
        }


    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}