package com.danielceinos.imgram.room

import androidx.room.Room
import com.danielceinos.imgram.data.room.ImageDao
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

object RoomDIModule {

    fun create() = DI.Module(name = this::class.simpleName!!, allowSilentOverride = false, init = builder)

    private val builder: DI.Builder.() -> Unit = {
        bind<AppDatabase>() with singleton {
            Room.databaseBuilder(instance(), AppDatabase::class.java, "imgram-db").build()
        }

        bind<ImageDao>() with singleton { instance<AppDatabase>().userDao() }
    }
}