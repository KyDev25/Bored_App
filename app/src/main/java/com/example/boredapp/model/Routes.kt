package com.example.boredapp.model

sealed class Routes(val route: String) {
    object HomeScreen : Routes("homeScreen")

    object FavoriteScreen : Routes("favoriteScreen")

    object DetailScreen : Routes("detailScreen")
}