package com.startzplay.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.startzplay.R
import com.startzplay.presentation.components.LoadImage
import com.startzplay.presentation.models.Movie
import com.startzplay.presentation.viewModels.DetailsScreenViewModel


@Composable
fun DetailsScreen(
    navController: NavController,
    movie: Movie?,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current
    val portrait = when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            true
        }

        else -> {
            false
        }
    }

    Column(
        Modifier
            .fillMaxHeight()
            .background(Color.Black)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
        )
        {
            LoadImage(
                url = movie?.imageUrl ?: "",
                modifier = Modifier.fillMaxSize(),
                scale = if (!portrait) ContentScale.Crop else ContentScale.FillBounds,
                emptyImagePlaceHolderSize = 200.dp
            )
            if(movie?.mediaType in listOf("movie", "tv")){
            Image(
                painter = painterResource(id = R.drawable.play_circle_outline_24),
                contentDescription = "",
                Modifier
                    .size(100.dp)
                    .clickable {
                        viewModel.launchPlayerActivity()
                    }
            )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .padding(top = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            ) {
                Text(
                    if(movie?.name.isNullOrEmpty())"No Title" else movie?.name?:"",
                    style = TextStyle(fontWeight = FontWeight.Bold, color = Color.White),
                    fontSize = 24.sp
                )

                Text(
                    if(movie?.overview.isNullOrEmpty())"No Title" else movie?.overview?:"",
                    style = TextStyle(color = Color.White),
                    fontSize = 14.sp,
                )

            }
        }
    }

}