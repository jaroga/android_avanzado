package com.danielceinos.imgram

import android.app.Application
import com.danielceinos.imgram.di.AppDIModule
import com.danielceinos.imgram.di.ImageDIModule
import com.danielceinos.imgram.di.SessionDIModule
import com.danielceinos.imgram.di.ViewModelDIModule
import com.danielceinos.imgram.networkutilities.NetworkDIModule
import com.danielceinos.imgram.room.RoomDIModule
import com.danielceinos.imgram.usecases.UseCasesDIModule
import com.facebook.stetho.Stetho
import org.kodein.di.DI
import org.kodein.di.DIAware

class App : Application(), DIAware {

    override val di by DI.lazy {
        import(AppDIModule(this@App).create())
        import(SessionDIModule.create())
        import(ImageDIModule.create())
        import(ViewModelDIModule.create())
        import(NetworkDIModule.create())
        import(RoomDIModule.create())
        import(UseCasesDIModule.create())
    }

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this);
    }
}
