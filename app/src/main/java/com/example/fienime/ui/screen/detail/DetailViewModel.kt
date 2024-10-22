package com.example.fienime.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fienime.common.UiState
import com.example.fienime.data.MovieAnimeRepository
import com.example.fienime.model.Anime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MovieAnimeRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Anime>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Anime>>
        get() = _uiState

    fun getAnimeById(animeId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getAnimeById(animeId))
        }
    }
}