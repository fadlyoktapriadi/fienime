package com.xeismonium.fienime.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.xeismonium.fienime.common.UiState
import com.xeismonium.fienime.di.Injection
import com.xeismonium.fienime.model.Anime
import com.xeismonium.fienime.ui.ViewModelFactory
import com.xeismonium.fienime.ui.screen.component.AnimeItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
){

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fienime") },
            )

        },
        content = { paddingValues ->
            viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        viewModel.getAllMoviesAnime()
                    }
                    is UiState.Success -> {
                        Home(
                            moviesAnime = uiState.data,
                            navController = navController,
                            modifier = modifier.padding(paddingValues)
                        )
                    }
                    is UiState.Error -> {}
                }
            }
        }
    )
}

@Composable
fun Home(
    moviesAnime: List<Anime>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        LazyColumn {
            items(moviesAnime) {
                AnimeItem(
                    image = it.image,
                    title = it.name,
                    genre = it.genre,
                    rating = it.rating,
                    desc = it.description,
                    modifier = Modifier.clickable {
                        navController.navigate("detail/${it.id}")
                    }
                )
            }
        }
    }
}