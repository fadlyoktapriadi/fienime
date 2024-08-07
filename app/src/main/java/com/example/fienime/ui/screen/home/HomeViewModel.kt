package com.example.fienime.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fienime.common.UiState
import com.example.fienime.data.MovieAnimeRepository
import com.example.fienime.model.Anime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MovieAnimeRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Anime>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Anime>>>
        get() = _uiState

    fun getAllMoviesAnime() {
        viewModelScope.launch {
            repository.getAllMoviesAnime()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { moviesAnime ->
                    _uiState.value = UiState.Success(moviesAnime)
                }
        }
    }
}