package com.example.githubsearchapp.domain.repository

import com.example.githubsearchapp.data.local.Repo
import com.example.githubsearchapp.data.local.User
import com.example.githubsearchapp.domain.util.Resource

interface GithubRepository {
    suspend fun getUser(text: String): Resource<User>
    suspend fun getRepos(text: String): Resource<List<Repo>>
}