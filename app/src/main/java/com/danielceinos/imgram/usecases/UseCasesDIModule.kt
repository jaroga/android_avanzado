package com.danielceinos.imgram.usecases

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

object UseCasesDIModule {
    fun create() = DI.Module(name = this::class.simpleName!!, allowSilentOverride = false, init = builder)

    private val builder: DI.Builder.() -> Unit = {
        bind<UploadImageUseCase>() with singleton { UploadImageUseCase(instance()) }
        bind<ShareImageUseCase>() with singleton { ShareImageUseCase(instance()) }
        bind<DeleteImageUseCase>() with singleton { DeleteImageUseCase(instance()) }
    }
}