package com.example.unsplashattestation.data.api

import com.example.unsplashattestation.data.api.photodto.PhotoDetailsDto
import com.example.unsplashattestation.data.api.photodto.PhotoListDto
import com.example.unsplashattestation.data.api.photodto.SearchDto
import com.example.unsplashattestation.data.api.photodto.WrapperPhotoDto
import retrofit2.http.*

interface ApiPhotos {

    @GET("photos")
    suspend fun getPhotos(@Query("page") page: Int): PhotoListDto

    @GET("photos/{id}")
    suspend fun getPhotoDetails(@Path("id") id: String): PhotoDetailsDto

    @POST("photos/{id}/like")
    suspend fun like(@Path("id") id: String): WrapperPhotoDto

    @DELETE("photos/{id}/like")
    suspend fun unlike(@Path("id") id: String): WrapperPhotoDto

    @GET("search/photos")
    suspend fun searchPhotos(@Query("query") query: String, @Query("page") page: Int): SearchDto

}