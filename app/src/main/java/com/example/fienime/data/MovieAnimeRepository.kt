package com.example.fienime.data

import com.example.fienime.model.Anime
import com.example.fienime.model.AnimeDataSource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.Flow

class MovieAnimeRepository {

    private val moviesAnime = mutableListOf<Anime>()

    init {
        if (moviesAnime.isEmpty()) {
            AnimeDataSource.dummyMovieAnime.forEach {
                moviesAnime.add(Anime(it.id, it.image, it.name, it.genre, it.rating, it.description))
            }
        }
    }

    fun getAllMoviesAnime( ): Flow<List<Anime>> {
        return flowOf(moviesAnime)
    }

    fun getAnimeById(animeId: Long): Anime {
        return moviesAnime.first {
            it.id == animeId
        }
    }

    companion object {
        @Volatile
        private var instance: MovieAnimeRepository? = null

        fun getInstance(): MovieAnimeRepository =
            instance ?: synchronized(this) {
                MovieAnimeRepository().apply {
                    instance = this
                }
            }
    }
}