package com.example.unsplashattestation.data.api

import retrofit2.http.POST
import retrofit2.http.Query

interface ApiToken {

    @POST("https://unsplash.com/oauth/token")
    suspend fun getToken(
        @Query("code") code: String,
        @Query("client_id") clientId: String = ACCESS_KEY,
        @Query("client_secret") clientSecret: String = SECRET_KEY,
        @Query("redirect_uri") redirectUri: String = REDIRECT_URI,
        @Query("grant_type") grantType: String = "authorization_code"
    ): ResponseToken
}

class ResponseToken(
    val access_token: String
)