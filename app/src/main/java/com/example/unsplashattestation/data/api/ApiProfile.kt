package com.example.unsplashattestation.data.api

import com.example.unsplashattestation.data.api.photodto.PhotoListDto
import com.example.unsplashattestation.data.api.profileDto.ProfileDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiProfile {

    @GET("me")
    suspend fun getProfile(): ProfileDto

    @GET("users/{username}/likes")
    suspend fun getProfileLikes(
        @Path("username") userName: String,
        @Query("page") page: Int
    ): PhotoListDto
}