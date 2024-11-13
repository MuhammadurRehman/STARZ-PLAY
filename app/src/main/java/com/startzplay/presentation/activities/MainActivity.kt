package com.startzplay.presentation.activities

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.google.gson.Gson
import com.startzplay.presentation.models.Movie
import com.startzplay.presentation.navigation.Screens
import com.startzplay.presentation.screens.DetailsScreen
import com.startzplay.presentation.screens.MainScreen
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                startDestination = Screens.MainScreen.route
            ) {
                composable(Screens.MainScreen.route) { MainScreen(navController) }

                composable(Screens.DetailsScreen.route+"/{movie}") {
                    val movieJson = it.arguments?.getString("movie")
                    val movie = movieJson?.let {
                        val decodedJson = URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
                        Gson().fromJson(decodedJson, Movie::class.java)
                    }
                    DetailsScreen(navController, movie) }

            }

        }
    }
}