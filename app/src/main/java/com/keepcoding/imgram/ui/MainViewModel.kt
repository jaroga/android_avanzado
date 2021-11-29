package com.keepcoding.imgram.ui

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.imgram.data.Repository
import com.keepcoding.imgram.mappers.TvShowPresentationMapper
import com.keepcoding.imgram.model.TvShowPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository, private val mapper: TvShowPresentationMapper) : ViewModel() {

    private val _images: MutableLiveData<List<TvShowPresentation>> by lazy {
        MutableLiveData<List<TvShowPresentation>>()
    }
    val images: LiveData<List<TvShowPresentation>> get() = _images

    private var coroutine: Job? = null

    init {
        getTvShows()
    }

    private fun getTvShows(){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getTvShows()
            }

            val sortedResults = result.sortedBy { it.name }

            _images.postValue(mapper.mapDataToPresentation(sortedResults))
        }
    }

    fun deleteTvShow(itemPresentation: TvShowPresentation){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
//                repository.deleteTvShow(mapper.mapPresentationToData(itemPresentation))
                itemPresentation.id?.also {
                    repository.deleteTvShowById(it)
                }

                repository.getTvShows()
            }

            _images.postValue(mapper.mapDataToPresentation(result))
        }
    }

    fun deleteAllTvShow(){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.deleteAllTvShows()
                repository.getTvShows()
            }

            _images.postValue(mapper.mapDataToPresentation(result))
        }
    }

    fun launchCoroutineGlobal2() {
        coroutine = GlobalScope.launch(Dispatchers.IO) {
            while (isActive) {
                Thread.sleep(1000)
                Log.d("MainViewModel", "Log desde while")
            }
        }
    }

    fun launchCoroutineInViewModel(){
        coroutine = viewModelScope.launch(Dispatchers.IO) {
            while (isActive) {
                Thread.sleep(1000)
                Log.d("MainViewModel", "Log desde while")
            }
        }
    }


    fun pararCoroutines() {
        coroutine?.cancel()
        coroutine = null
    }

    fun launchCoroutineGlobal(context: AppCompatActivity) {
//        GlobalScope.launch {
//            val resultado = return10()
//            Log.d("MainViewModel", "Resultado: $resultado")
//            Thread.sleep(5000)
//            Toast.makeText(context, "$resultado", Toast.LENGTH_LONG).show()
//            launch1000Threads()
//        }

//        val coroutine = GlobalScope.launch(Dispatchers.Main) {
//            Log.d("MainViewModel", "Lanzando corutina de IO")
//            val resultado = withContext(Dispatchers.IO){
//                Log.d("MainViewModel", "Antes de suspend")
//                val result = return10()
//                Log.d("MainViewModel", "Despues de suspend")
//                return@withContext result
//            }
//            Log.d("MainViewModel", "Resultado")
//            Log.d("MainViewModel", "Resultado: $resultado")
//            Toast.makeText(context, "$resultado", Toast.LENGTH_LONG).show()
//        }

//        val coroutine = GlobalScope.launch(Dispatchers.Main) {
//            Log.d("MainViewModel", "Lanzando corutina de IO")
//
//            val resultado1 = async(Dispatchers.IO){
//                Log.d("MainViewModel", "Antes 1 de suspend")
//                val result = tarea1()
//                Log.d("MainViewModel", "Despues 1 de suspend")
//                return@async result
//            }
//
//            val resultado2 = async(Dispatchers.IO){
//                Log.d("MainViewModel", "Antes 2 de suspend")
//                val result = tarea2()
//                Log.d("MainViewModel", "Despues 2 de suspend")
//                return@async result
//            }
//
//            val resultado3 = async(Dispatchers.IO){
//                Log.d("MainViewModel", "Antes 3 de suspend")
//                val result = tarea3()
//                Log.d("MainViewModel", "Despues 3 de suspend")
//                return@async result
//            }
//            val resultado5 = async(Dispatchers.IO) {
//                Log.d("MainViewModel", "Antes 5 de suspend")
//                val result = tarea5()
//                Log.d("MainViewModel", "Despues 5 de suspend")
//                return@async result
//            }
//
//            val resultado4 = async(Dispatchers.IO) {
//                Log.d("MainViewModel", "Antes 4 de suspend")
//                val result = tarea4(resultado5.await())
//                Log.d("MainViewModel", "Despues 4 de suspend")
//                return@async result
//            }
//
//            Log.d("MainViewModel", "Antes del resultado")
//            val a = "y tu?"
//            Log.d("MainViewModel", "Variable: $a")
//            Log.d("MainViewModel", "Variable deferred: $resultado2")
//
//            val resultadoParcial12 = "${resultado1.await()} ${resultado2.await()}"
//            Log.d("MainViewModel", "Resultado parcial 12: $resultadoParcial12")
//            val resultadoParcial34 = "${resultado3.await()} ${resultado4.await()}"
//            Log.d("MainViewModel", "Resultado parcial 34: $resultadoParcial34")
//            val resultadoParcial5 = "${resultado5.await()}"
//            Log.d("MainViewModel", "Resultado parcial 5: $resultadoParcial5")
//
//            val resutado6 = withContext(Dispatchers.IO){
//                val resultado5 = tarea5()
//                val resultado4 = tarea4(5)
//            }
//
//            val resultado = "${resultado1.await()}${resultado2.await()} ${resultado3.await()} $a ${resultado4.await()} ${resultado5.await()}"
////            val resultado = "${resultado1}${resultado2} ${resultado3.await()} $a"
//            // QUiero que imprimas "HOLA, QUE TAL?"
//            Log.d("MainViewModel", "Resultado: $resultado")
//            Toast.makeText(context, "$resultado", Toast.LENGTH_LONG).show()
//        }
    }

    private fun launch1000Threads() {
        Log.d("MainViewModel", "Creando 5 hilos")
        for (i in 0..100) {
            Log.d("MainViewModel", "Creando $i hilo")
            GlobalScope.launch(Dispatchers.IO) {
                val resultado = return10()
                val sleepTime = 1000 * i
                while (true) {
                    Thread.sleep(sleepTime.toLong())
                    Log.d("MainViewModel", "Resultado $i: $resultado")
                }
            }
        }
    }

    private suspend fun return10(): String {
        Thread.sleep(4000)
        return return101().toString()
    }

    private suspend fun return101(): Int {
        return 101
    }

    private suspend fun tarea1(): String {
        Thread.sleep(1000)
        return "HOLA"
    }

    private suspend fun tarea2(): String {
        Thread.sleep(2000)
        return ","
    }

    private suspend fun tarea3(): String {
        Thread.sleep(3000)
        return "QUE TAL?"
    }

    private suspend fun tarea4(number: Int): String {
        var resultado = ""
        for (i in 0..number) {
            resultado += "$i"
        }

        Thread.sleep(2000)
        return resultado
    }

    private suspend fun tarea5(): Int {
        val rnds = (0..10).random()
        Thread.sleep(3000)

        return rnds
    }

    private suspend fun tarea6() {


    }

    fun lanzarThread(callback: (Long) -> (Unit)){
        Thread {
            val LIMIT = 100000
            var i = 0L

            while (i < LIMIT){
                val rnds = (1..1000).random()
                Log.d("MainViewModel", "Previous: $i")
                Thread.sleep(100)
                i += rnds
                Log.d("MainViewModel", "After: $i Se ha sumado $rnds")
            }

            callback(i)
        }.start()
    }


//    private fun return101(): Int = 101
//
//    private fun return1010() = 1010
}