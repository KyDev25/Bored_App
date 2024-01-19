package com.example.boredapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.boredapp.model.MainViewModel
import com.example.boredapp.model.Routes
import com.example.boredapp.model.returnImage


//Composable représentant l'écran d'accueil pour générer une activité
@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Activity(navHostController = navHostController, viewModel = viewModel)
        ButtonGenerate(viewModel)
        ButtonFavorite(navHostController)
    }
}

@Composable
fun Activity(modifier: Modifier = Modifier, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(), navHostController: NavHostController){
    ElevatedCard (
        modifier = modifier
            .padding(50.dp)
            .size(350.dp),

    ) {
        Image(
            painter = painterResource(id = returnImage(viewModel.data?.type ?: "")),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = viewModel.data?.type ?: "",
                    fontSize = 30.sp,
                    color = Color.Blue
                )
                Text(
                    text = "${viewModel.data?.activity?.take(30) ?: "..."}...",
                )
            }
            Row (
                horizontalArrangement = Arrangement.spacedBy(260.dp),
            ) {
                ButtonDetail(navHostController)
                ButtonAdd(viewModel)
            }
        }
    }
}

@Composable
fun ButtonGenerate(viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    Button(
        onClick = {viewModel.loadData()},
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
        ) {
        Icon(
            Icons.Filled.Refresh,
            contentDescription = "Generate Activity",
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = "Generate Activity")
    }
}

@Composable
fun ButtonDetail( navHostController: NavHostController){
//    Button(
//        onClick = {navHostController.navigate(Routes.DetailScreen.route)},
//    ) {
//        Icon(
//            Icons.Filled.MoreVert,
//            contentDescription = "More Detail",
//            modifier = Modifier.size(ButtonDefaults.IconSize)
//        )
//    }
    Icon(
        Icons.Filled.MoreVert,
        contentDescription = "More Detail",
        modifier = Modifier
            .clickable { navHostController.navigate(Routes.DetailScreen.route) }
    )
}

@Composable
fun ButtonAdd( viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
//    Button(
//        onClick = {viewModel.addFavorite()},
//    ) {
//        Icon(
//            Icons.Filled.Add,
//            contentDescription = "Add Activity",
//            modifier = Modifier.size(ButtonDefaults.IconSize)
//        )
//    }

    if(viewModel.data in viewModel.myList){
        viewModel.favoriteIcon = true
    } else {
        viewModel.favoriteIcon = false
    }

    if(!viewModel.favoriteIcon){
        Icon(
            Icons.Filled.FavoriteBorder,
            contentDescription = "Add Favorite",
            modifier = Modifier.clickable { viewModel.addFavorite() }
        )
    } else{
        Icon(
            Icons.Filled.Favorite,
            contentDescription = "Remove Favorite",
            modifier = Modifier.clickable { viewModel.removeFavorite(viewModel.data) }
        )
    }
}

@Composable
fun ButtonFavorite( navHostController: NavHostController){
    Button(
        onClick = {navHostController.navigate(Routes.FavoriteScreen.route)},
    ) {
        Icon(
            Icons.Filled.Star,
            contentDescription = "Favorites",
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
    }
}