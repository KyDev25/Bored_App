package com.example.boredapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.boredapp.model.BoredBean
import com.example.boredapp.model.MainViewModel
import com.example.boredapp.model.Routes
import com.example.boredapp.model.returnImage

//Composable représentant toutes les activités ajoutées aux favoris
@Composable
fun FavoriteScreen(navHostController: NavHostController, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val searValues = viewModel.myList.filter { it?.type?.contains(viewModel.searchText, true) ?: false }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(viewModel= viewModel)
        LazyColumn (verticalArrangement = Arrangement.spacedBy(8.dp)) {
            println("FAVORITE SCREEN / ${viewModel.myList.size}")
            items(searValues.size) {
                OneActivity(data = searValues[it], navHostController = navHostController, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun OneActivity(modifier: Modifier = Modifier, data: BoredBean?, navHostController: NavHostController, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    ElevatedCard (
        modifier = modifier
            .padding(50.dp)
            .size(350.dp),

        ) {
        Image(
            painter = painterResource(id = returnImage(data?.type ?: "")),
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
                    text = data?.type ?: "",
                    fontSize = 30.sp,
                    color = Color.Blue
                )
                Text(
                    "${viewModel.data?.activity?.take(30) ?: "..."}..."
                )
            }
            Row (
                horizontalArrangement = Arrangement.spacedBy(260.dp),
            ) {
                ButtonBack(navHostController)
                ButtonRemove(viewModel = viewModel, data = data)
            }
        }
    }
}

@Composable
fun ButtonBack( navHostController: NavHostController){
//    Button(
//        onClick = {navHostController.navigate(Routes.HomeScreen.route)},
//    ) {
//        Icon(
//            Icons.Filled.ArrowBack,
//            contentDescription = "Back",
//            modifier = Modifier.size(ButtonDefaults.IconSize)
//        )
//    }
    Icon(
        Icons.Filled.ArrowBack,
        contentDescription = "Back",
        modifier = Modifier.clickable { navHostController.navigate(Routes.HomeScreen.route) }
    )
}

@Composable
fun ButtonRemove(viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(), data: BoredBean?){
//    Button(
//        onClick = {viewModel.removeFavorite(data)},
//    ) {
//        Icon(
//            Icons.Filled.Close,
//            contentDescription = "Remove",
//            modifier = Modifier.size(ButtonDefaults.IconSize)
//        )
//    }
    Icon(
        Icons.Filled.Close,
        contentDescription = "Remove",
        modifier = Modifier.clickable { viewModel.removeFavorite(data) }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    TextField(
        value = viewModel.searchText,
        onValueChange = {newValue -> viewModel.uploadSearchText(newValue)},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        },
        placeholder = {
            Text("Recherche")
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .animateContentSize(),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            viewModel.uploadSearchText(viewModel.searchText)
            keyboardController?.hide()
            focusManager.clearFocus()
        })
    )
}