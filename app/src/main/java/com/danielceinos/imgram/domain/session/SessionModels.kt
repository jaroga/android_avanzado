package com.danielceinos.imgram.domain.session

data class Session(
    val accessToken: String,
    val expiresAt: Long,
    val tokenType: String,
    val refreshToken: String,
    val accountUsername: String,
    val accountId: String
)