package com.danielceinos.imgram.di

import com.danielceinos.imgram.data.session.SessionLocalDataSource
import com.danielceinos.imgram.data.session.SessionMemoryDataSource
import com.danielceinos.imgram.domain.session.SessionRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

object SessionDIModule {

    fun create() = DI.Module(name = this::class.simpleName!!, allowSilentOverride = false, init = builder)

    private val builder: DI.Builder.() -> Unit = {
        bind<SessionRepository>() with singleton { SessionRepository(instance(), instance()) }
        bind<SessionLocalDataSource>() with singleton { SessionLocalDataSource(instance()) }
        bind<SessionMemoryDataSource>() with singleton { SessionMemoryDataSource() }
    }
}