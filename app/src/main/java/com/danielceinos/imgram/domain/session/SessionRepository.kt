package com.danielceinos.imgram.domain.session

import com.danielceinos.imgram.data.session.SessionLocalDataSource
import com.danielceinos.imgram.data.session.SessionMemoryDataSource
import com.danielceinos.imgram.utils.extensions.alsoIfTrue

class SessionRepository(
    private val localDataSource: SessionLocalDataSource,
    private val memoryDataSource: SessionMemoryDataSource
) {

    fun getSession(): Session? {
        return memoryDataSource.getSession() ?: localDataSource.getSession()?.also { memoryDataSource.saveSession(it) }
    }

    fun saveSessionFromOauth2(url: String): Boolean {
        return "imgram://oauth2.+".toRegex().matches(url).alsoIfTrue {
            val session = Session(
                accessToken = "access_token=(\\w+)".toRegex().find(url)!!.groupValues[1],
                expiresAt = "expires_in=(\\w+)".toRegex().find(url)!!.groupValues[1].toLong() + System.currentTimeMillis(),
                tokenType = "token_type=(\\w+)".toRegex().find(url)!!.groupValues[1],
                refreshToken = "refresh_token=(\\w+)".toRegex().find(url)!!.groupValues[1],
                accountUsername = "account_username=(\\w+)".toRegex().find(url)!!.groupValues[1],
                accountId = "account_id=(\\w+)".toRegex().find(url)!!.groupValues[1]
            )
            memoryDataSource.saveSession(session)
            localDataSource.saveSession(session)
        }
    }
}