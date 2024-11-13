package com.startzplay.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.startzplay.presentation.components.CarouselItem
import com.startzplay.presentation.components.SearchBox
import com.startzplay.presentation.navigation.Screens
import com.startzplay.presentation.viewModels.MainScreenViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {

    val movies = viewModel.moviesState.collectAsStateWithLifecycle()


    Scaffold { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
        ) {

            SearchBox(modifier = Modifier.fillMaxWidth()) {
                if (it.isNotEmpty())
                    viewModel.searchQuery(it)
            }
            if (movies.value.isEmpty()) {
                Text(
                    "No Movies",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium)
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    movies.value.forEach { (type, movies) ->
                        item {
                            CarouselItem(
                                type,
                                movies
                            ) { movie ->

                                val movieJson = Gson().toJson(movie)
                                val encodedMovieJson =
                                    URLEncoder.encode(movieJson, StandardCharsets.UTF_8.toString())
                                navController.navigate(Screens.DetailsScreen.route + "/$encodedMovieJson")
                            }
                        }
                    }
                }
            }
        }
    }
}