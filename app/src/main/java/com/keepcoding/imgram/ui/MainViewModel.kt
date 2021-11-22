package com.keepcoding.imgram.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keepcoding.imgram.model.Image


class MainViewModel: ViewModel() {

    private val _images: MutableLiveData<Image> by lazy {
        MutableLiveData<Image>()
    }

    val images: LiveData<Image> get() = _images


    fun createImages(){

    }
}