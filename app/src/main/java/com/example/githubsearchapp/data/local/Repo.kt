package com.example.githubsearchapp.data.local

import com.squareup.moshi.Json

data class Repo(
    @Json(name = "description")
    val description: String? = "",
    @Json(name = "forks")
    val forks: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "stargazers_count")
    val stargazersCount: String,
    @Json(name = "updated_at")
    val updatedAt: String
)