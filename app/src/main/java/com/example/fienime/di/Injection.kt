package com.example.fienime.di

import com.example.fienime.data.MovieAnimeRepository

object Injection {
    fun provideRepository(): MovieAnimeRepository {
        return MovieAnimeRepository.getInstance()
    }
}