package com.keepcoding.imgram.di

import android.content.Context
import androidx.room.Room
import com.keepcoding.imgram.data.local.TheMovieDBDAO
import com.keepcoding.imgram.data.local.TheMovieDBDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun provideTheMovieDBDatabase(@ApplicationContext context: Context): TheMovieDBDatabase {
        return Room.databaseBuilder(
            context,
            TheMovieDBDatabase::class.java, "the_movie_db_example"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTheMovieDBDAO(database: TheMovieDBDatabase): TheMovieDBDAO {
        return database.tmdbDao()
    }
}