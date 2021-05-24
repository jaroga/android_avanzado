package com.danielceinos.imgram.data.imgurapi

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*


interface ImgurApi {

    @Headers("Authorization: Client-ID 3795c60af5383c1")
    @GET("gallery/hot/")
    suspend fun getHotGallery(): ImgurResponse<List<ImgurGallery>>

    @Headers("Authorization: Client-ID 3795c60af5383c1")
    @GET("gallery/top/")
    suspend fun getTopGallery(): ImgurResponse<List<ImgurGallery>>

    @GET("account/{username}/images/")
    suspend fun getUsernameImages(@Path("username") username: String): ImgurResponse<List<ImgurImage>>

    @GET("account/{username}/submissions/")
    suspend fun getUsernameGalleries(@Path("username") username: String): ImgurResponse<List<ImgurGallery>>

    @FormUrlEncoded
    @POST("gallery/image/{imageHash}")
    suspend fun shareImage(
        @Path("imageHash") imageHash: String,
        @Field("title") title: String,
        @Field("tags") tags: String,
    ): Response<Unit>

    @Multipart
    @POST("upload/")
    suspend fun uploadImage(@Part image: MultipartBody.Part): ImgurResponse<ImgurImage>

    @DELETE("image/{imageHash}/")
    suspend fun deleteImage(@Path("imageHash") imageHash: String): Response<Unit>
}