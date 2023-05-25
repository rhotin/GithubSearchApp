package com.example.githubsearchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.githubsearchapp.ui.theme.GithubSearchAppTheme
import com.example.githubsearchapp.ui.views.githubSearchScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubSearchAppTheme {
                githubSearchScreen()
            }
        }
    }
}