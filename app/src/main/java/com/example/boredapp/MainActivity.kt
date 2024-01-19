package com.example.boredapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.boredapp.model.MainViewModel
import com.example.boredapp.model.Routes
import com.example.boredapp.ui.screens.DetailScreen
import com.example.boredapp.ui.screens.FavoriteScreen
import com.example.boredapp.ui.screens.HomeScreen
import com.example.boredapp.ui.theme.BoredAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoredAppTheme(dynamicColor = false) {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.DarkGray) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {

    val navController : NavHostController = rememberNavController()
    val viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {

        //Route 1 vers HomeScreen
        composable(Routes.HomeScreen.route) {
            HomeScreen(navHostController = navController, viewModel = viewModel)
        }

        //Route 2 vers FavoriteScreen
        composable(Routes.FavoriteScreen.route) {
            FavoriteScreen(navHostController = navController, viewModel = viewModel)
        }

        //Route 3 vers DetailScreen
        composable(route = Routes.DetailScreen.route,) {
            DetailScreen(navHostController = navController, viewModel= viewModel)
        }
    }
}


