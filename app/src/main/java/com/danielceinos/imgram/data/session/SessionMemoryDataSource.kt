package com.danielceinos.imgram.data.session

import com.danielceinos.imgram.domain.session.Session

class SessionMemoryDataSource {
    private var session: Session? = null

    fun getSession() = session
    fun saveSession(session: Session) {
        this.session = session
    }

}