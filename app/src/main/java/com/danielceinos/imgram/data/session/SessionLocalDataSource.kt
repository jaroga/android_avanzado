package com.danielceinos.imgram.data.session

import android.content.SharedPreferences
import com.danielceinos.imgram.domain.session.Session
import com.danielceinos.imgram.data.session.SessionLocalDataSource.SessionKey.*
import com.danielceinos.imgram.utils.extensions.getLong
import com.danielceinos.imgram.utils.extensions.getString

class SessionLocalDataSource(private val sharedPreferences: SharedPreferences) {

    private enum class SessionKey {
        ACCESS_TOKEN_KEY, EXPIRES_AT_KEY, TOKEN_TYPE_KEY, REFRESH_TOKEN_KEY, ACCOUNT_USERNAME_KEY, ACCOUNT_ID_KEY
    }

    fun getSession(): Session? {
        if (!hasSession()) return null

        val accessToken = sharedPreferences.getString("$ACCESS_TOKEN_KEY")
        val expiresAt = sharedPreferences.getLong("$EXPIRES_AT_KEY")
        val tokenType = sharedPreferences.getString("$TOKEN_TYPE_KEY")
        val refreshToken = sharedPreferences.getString("$REFRESH_TOKEN_KEY")
        val accountUsername = sharedPreferences.getString("$ACCOUNT_USERNAME_KEY")
        val accountId = sharedPreferences.getString("$ACCOUNT_ID_KEY")

        return Session(
            accessToken = accessToken,
            expiresAt = expiresAt,
            tokenType = tokenType,
            refreshToken = refreshToken,
            accountUsername = accountUsername,
            accountId = accountId
        )
    }

    fun saveSession(session: Session) {
        sharedPreferences.edit().putString("$ACCESS_TOKEN_KEY", session.accessToken).apply()
        sharedPreferences.edit().putLong("$EXPIRES_AT_KEY", session.expiresAt).apply()
        sharedPreferences.edit().putString("$TOKEN_TYPE_KEY", session.tokenType).apply()
        sharedPreferences.edit().putString("$REFRESH_TOKEN_KEY", session.refreshToken).apply()
        sharedPreferences.edit().putString("$ACCOUNT_USERNAME_KEY", session.accountUsername).apply()
        sharedPreferences.edit().putString("$ACCOUNT_ID_KEY", session.accountId).apply()
    }

    private fun hasSession() = sharedPreferences.getString("$ACCESS_TOKEN_KEY", null) != null
}