package com.xeismonium.fienime.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fienime.R
import com.xeismonium.fienime.common.UiState
import com.xeismonium.fienime.di.Injection
import com.xeismonium.fienime.model.Anime
import com.xeismonium.fienime.ui.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavHostController,
    animeId: String,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Movie") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.back_button),
                            contentDescription = "Back Button"
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        viewModel.getAnimeById(animeId.toLong())
                    }
                    is UiState.Success -> {
                        Detail(
                            anime = uiState.data,
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
fun Detail(
    anime: Anime,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(anime.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = anime.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = anime.genre,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = anime.rating,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = anime.description,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

    }
}