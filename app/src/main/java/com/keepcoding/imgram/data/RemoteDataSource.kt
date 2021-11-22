package com.keepcoding.imgram.data

import okhttp3.OkHttpClient
import okhttp3.Request

class RemoteDataSource {

    val client = OkHttpClient().newBuilder().build()

    fun getImage(url: String): String{
        val request: Request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()

        val jsonResponse = response.body.toString()


        return jsonResponse
    }
}

