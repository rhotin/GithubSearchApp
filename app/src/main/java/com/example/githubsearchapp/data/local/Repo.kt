package com.example.githubsearchapp.data.local

data class Repo(
    val description: String? = "",
    val forks: String,
    val name: String,
    val stargazers_count: String,
    val updated_at: String
)