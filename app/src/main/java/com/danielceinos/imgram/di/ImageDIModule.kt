package com.danielceinos.imgram.di

import com.danielceinos.imgram.data.imgurapi.ImgurApi
import com.danielceinos.imgram.domain.image.ImageRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import retrofit2.Retrofit

object ImageDIModule {
    fun create() = DI.Module(name = this::class.simpleName!!, allowSilentOverride = false, init = builder)

    private val builder: DI.Builder.() -> Unit = {
        bind<ImageRepository>() with singleton { ImageRepository(instance(), instance()) }
        bind<ImgurApi>() with singleton { instance<Retrofit>().create(ImgurApi::class.java) }
    }
}