package com.keepcoding.imgram.di

import android.content.Context
import com.keepcoding.imgram.BuildConfig
import com.keepcoding.imgram.Properties
import com.keepcoding.imgram.data.remote.TheMovieDBApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val myCache = Cache(context.cacheDir, Properties.CACHE_SIZE_BYTES)
        val clientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        clientBuilder.cache(myCache)
        clientBuilder.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", Properties.API_KEY).build()

            val requestBuilder = original.newBuilder()
                .header("Cache-Control", "public, max-age=${Properties.MAX_SECONDS_VALID_CACHE}")
                .url(url)

            val request = requestBuilder.build()

            chain.proceed(request)
        }
        clientBuilder.readTimeout(Properties.NETWORK_CLIENT_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(Properties.NETWORK_CLIENT_TIMEOUT, TimeUnit.SECONDS)
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Properties.BASE_URL)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideTheMovieDBApi(retrofit: Retrofit): TheMovieDBApi {
        return retrofit.create(TheMovieDBApi::class.java)
    }

}