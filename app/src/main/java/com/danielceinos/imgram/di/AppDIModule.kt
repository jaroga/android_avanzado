package com.danielceinos.imgram.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.*
import org.kodein.type.erased

class AppDIModule(private val application: Application) {

    fun create() = DI.Module(name = this::class.simpleName!!, allowSilentOverride = false, init = builder)

    private val builder: DI.Builder.() -> Unit = {
        bind<Application>() with singleton { application }

        bind<SharedPreferences>() with singleton { instance<Application>().getSharedPreferences("shared", Context.MODE_PRIVATE) }
        bind<ViewModelProvider.Factory>() with singleton { DIViewModelFactory(di) }
    }
}

class DIViewModelFactory(private val di: DI) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = di.direct.Instance(erased(modelClass))
}