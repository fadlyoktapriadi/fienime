package com.xeismonium.fienime.di

import com.xeismonium.fienime.data.MovieAnimeRepository

object Injection {
    fun provideRepository(): MovieAnimeRepository {
        return MovieAnimeRepository.getInstance()
    }
}