package com.example.unsplashattestation.domain.model

import com.example.unsplashattestation.data.api.photodto.*
import com.google.gson.annotations.SerializedName

data class PhotoDetails(
    val id: String,
    val downloads: Int,
    val likes: Int,
    val likedByUser: Boolean,
    val exif: Exif,
    val location: Location,
    val tags: List<Tags>,
    val urls: Urls,
    val user: User
)

data class Location(
    val city: String?,
    val position: Position
)

data class Tags(
    val title: String?
)

data class Exif(
    val make: String?,
    val model: String?,
    val name: String?,
    val exposureTime: String?,
    val aperture: String?,
    val focalLength: String?,
    val iso: Int?
)

data class Position(
    val latitude: Double?,
    val longitude: Double?
)

data class Urls(
    val raw: String,
    val regular: String
)

data class User(
    val bio: String?,
    val name: String,
    @SerializedName("profile_image")
    val profileImage: ProfileImage,
    val username: String
)

data class ProfileImage(
    val small: String
)