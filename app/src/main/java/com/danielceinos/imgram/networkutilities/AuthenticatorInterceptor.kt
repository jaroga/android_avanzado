package com.danielceinos.imgram.networkutilities

import com.danielceinos.imgram.domain.session.SessionRepository
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticatorInterceptor(private val sessionRepository: SessionRepository): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val session = sessionRepository.getSession()

        return if (session == null) {
            chain.proceed(request)
        } else {
            val authorizedRequest = request.newBuilder()
                .header("Authorization", "${session.tokenType.capitalize()} ${session.accessToken}")
            return chain.proceed(authorizedRequest.build())
        }
    }
}