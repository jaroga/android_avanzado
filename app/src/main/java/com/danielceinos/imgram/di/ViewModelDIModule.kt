package com.danielceinos.imgram.di

import com.danielceinos.imgram.ui.home.HomeViewModel
import org.kodein.di.*

object ViewModelDIModule {

    fun create() = DI.Module(name = this::class.simpleName!!, allowSilentOverride = false, init = builder)

    private val builder: DI.Builder.() -> Unit = {
        bind<HomeViewModel>() with provider { HomeViewModel(instance(), instance(), instance(), instance(), instance()) }
    }
}