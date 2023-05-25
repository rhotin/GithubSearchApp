package com.example.githubsearchapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubsearchapp.data.local.Repo
import com.example.githubsearchapp.data.local.User
import com.example.githubsearchapp.domain.repository.GithubRepository
import com.example.githubsearchapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _user = MutableStateFlow(User())
    val user = _user.asStateFlow()

    private val _repo = MutableStateFlow(listOf<Repo>())
    val repo = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_repo) { text, repos ->
            callApi(text)
            repos
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _repo.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    private fun callApi(userId: String){
        viewModelScope.launch {
            when (val resultUser = repository.getUser(userId)) {
                is Resource.Error -> {
                    resultUser.message
                }

                is Resource.Success -> {
                    _user.value = resultUser.data ?: User()
                    when (val resultRepo = repository.getRepos(userId)) {
                        is Resource.Error -> {
                            resultRepo.message
                        }

                        is Resource.Success -> {
                            _repo.value = resultRepo.data ?: emptyList()
                        }
                    }
                }
            }
        }
    }
}