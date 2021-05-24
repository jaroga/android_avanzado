package com.danielceinos.imgram.networkutilities

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkDIModule {

    fun create() = DI.Module(name = this::class.simpleName!!, allowSilentOverride = false, init = builder)

    private val builder: DI.Builder.() -> Unit = {
        bind<OkHttpClient>() with singleton {
            val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            val authInterceptor = instance<AuthenticatorInterceptor>()
            return@singleton OkHttpClient().newBuilder()
                .addInterceptor(logging)
                .addNetworkInterceptor(StethoInterceptor())
                .addInterceptor(authInterceptor)
                .build()
        }
        bind<Retrofit>() with singleton {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

            return@singleton Retrofit.Builder()
                .baseUrl("https://api.imgur.com/3/")
                .client(instance())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        bind<AuthenticatorInterceptor>() with singleton { AuthenticatorInterceptor(instance()) }
    }
}