package com.example.githubsearchapp.domain.repository

import com.example.githubsearchapp.data.local.Repo
import com.example.githubsearchapp.data.local.User
import com.example.githubsearchapp.domain.util.Resource

interface GithubRepository {
    suspend fun getUser(userId: String): Resource<User>
    suspend fun getRepos(userId: String): Resource<List<Repo>>
}