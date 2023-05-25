package com.example.githubsearchapp.data.repository

import com.example.githubsearchapp.data.local.Repo
import com.example.githubsearchapp.data.local.User
import com.example.githubsearchapp.data.remote.GithubApi
import com.example.githubsearchapp.domain.repository.GithubRepository
import com.example.githubsearchapp.domain.util.Resource
import java.lang.Exception
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : GithubRepository {
    override suspend fun getUser(userId: String): Resource<User> {
        return try {
            Resource.Success(
                data = api.getUser(userId)
            )
        }catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e.message?:"An unknown error occurred.")
        }
    }

    override suspend fun getRepos(userId: String): Resource<List<Repo>> {
        return try {
            Resource.Success(
                data = api.getRepos(userId)
            )
        }catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e.message?:"An unknown error occurred.")
        }
    }
}