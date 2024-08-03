package com.xeismonium.fienime.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fienime.R
import com.xeismonium.fienime.ui.theme.FienimeTheme

@Composable
fun AnimeItem(
    image: Int,
    title: String,
    genre: String,
    rating: String,
    desc: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .padding(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 4.dp)
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier) {
            Image(
                painter = painterResource(image),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(110.dp)
                    .clip(MaterialTheme.shapes.small)
                    .padding(end = 8.dp)
            )
            Box(modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .padding(8.dp)
                .align(Alignment.BottomEnd)
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
                )
            ) {
                Text(
                    text = rating,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 12.dp, start = 4.dp)
                )
            }
        }

        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = genre,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal,
                modifier = modifier
                    .padding(bottom = 4.dp)
            )
            Text(
                text = desc,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AnimeItemPreview() {
    FienimeTheme {
        AnimeItem(R.drawable.anime1, "Awas ada Meteor", "Action, Adventure", "4.7/5", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
    }
}