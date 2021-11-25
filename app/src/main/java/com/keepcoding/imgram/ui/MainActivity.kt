package com.keepcoding.imgram.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationBarView
import com.keepcoding.imgram.R
import com.keepcoding.imgram.ThreadManager
import com.keepcoding.imgram.databinding.ActivityMainBinding
import com.keepcoding.imgram.managers.MainManager
import com.keepcoding.imgram.model.Image
import com.keepcoding.imgram.visible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var imageAdapter = ImageAdapter()

//    private val viewModel: MainViewModel by lazy {
//        ViewModelProvider(this).get(MainViewModel::class.java)
//    }

    private val viewModel: MainViewModel by viewModels()

    //private lateinit var lateinitViewModel: MainViewModel

    private val mainManager = MainManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            imageList.adapter = imageAdapter
//            imageList.layoutManager =
//                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

            imageList.layoutManager = GridLayoutManager(this@MainActivity, 2)

            // Error de lateinit no inicializado
            //lateinitViewModel.getHola()

            button.setOnClickListener {
//                viewModel.launchCoroutineInViewModel()
//                mainManager.diTuVariable(
//                viewModel.getTvShows()
            }

            bottomNavigationView.setOnItemSelectedListener(object: NavigationBarView.OnItemSelectedListener {
                override fun onNavigationItemSelected(item: MenuItem): Boolean {

                    when(item.itemId){
                        R.id.menu_top -> {
                            Log.d("ActivityMain", "Menu top clicked")
                        }
                        R.id.menu_hot -> {
                            Log.d("ActivityMain", "Menu hot clicked")

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

        binding.progress.visible(true)

        viewModel.images.observe(this){
            imageAdapter.addAll(it)
            binding.progress.visible(false)
        }
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
}