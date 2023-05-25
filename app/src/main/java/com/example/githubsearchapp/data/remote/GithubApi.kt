package com.example.githubsearchapp.data.remote

import com.example.githubsearchapp.data.local.Repo
import com.example.githubsearchapp.data.local.User
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: String): User

    @GET("users/{userId}/repos")
    suspend fun getRepos(@Path("userId") userId: String): List<Repo>

}