package com.keepcoding.imgram.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.keepcoding.imgram.ThreadManager
import com.keepcoding.imgram.databinding.ActivityMainBinding
import com.keepcoding.imgram.model.Image

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var imageAdapter = ImageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            imageList.adapter = imageAdapter
            imageList.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

            button.setOnClickListener { crearCallback() }
        }

        val images = mutableListOf<Image>()

        for (i in 0..20){
            images.add(Image(i.toString(), "Title $i", "https://picsum.photos/200/300"))
        }

//        Thread.sleep(100000)

        imageAdapter.data = images
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