package com.example.githubsearchapp.data.local

import com.squareup.moshi.Json

data class User(
    @Json(name = "login")
    val accountName: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "avatar_url")
    val avatarUrl: String
)
