package com.keepcoding.imgram.managers

import android.util.Log

class MainManager {

    val localManager = LocalManager()
    val networkManager = NetworkManager()

    fun diTuVariable(){
        localManager.diTuVariable()
        networkManager.diTuVariable()
    }
}

class LocalManager {
    val sharedPreferenceManager = SharedPreferenceManager()
    val roomManager = RoomManager("adios")

    fun diTuVariable(){
        sharedPreferenceManager.diTuVariable()
        roomManager.diTuVariable()
    }
}

class SharedPreferenceManager {
    val hola = "HOLA"

    fun diTuVariable(){
        Log.d("SharedPreferenceManager", hola)
    }
}

class RoomManager(private val adios: String) {
    fun diTuVariable(){
        Log.d("RoomManager", adios)
    }
}

class NetworkManager {

    val quetal = "QUETAL"

    fun diTuVariable(){
        Log.d("NetworkManager", quetal)
    }
}

