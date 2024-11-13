package com.startzplay.presentation.navigation

sealed class Screens (val route:String) {
    object MainScreen: Screens("MainScreen")
    object DetailsScreen: Screens("DetailsScreen")
    object PlayerScreen: Screens("PlayerScreen")
}