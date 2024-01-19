package com.example.boredapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.boredapp.model.MainViewModel
import com.example.boredapp.model.returnImage

//Composable représentant le détail d'une activité
@Composable
fun DetailScreen(navHostController: NavHostController, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        DetailActivity(viewModel = viewModel, navHostController = navHostController)
    }
}

@Composable
fun DetailActivity(viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(), navHostController: NavHostController){
    val localUriHandler = LocalUriHandler.current
    var strLink: String? = viewModel.data?.link
    var link:String? = "https://www.boredapi.com/"
    if(strLink == ""){
            strLink = "No Link"
        } else{
            link = strLink
    }
        Image(
            painter = painterResource(id = returnImage(viewModel.data?.type ?: "")),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(30.dp)
        ) {
            Text(
                text = viewModel.data?.type?.uppercase() ?: "",
                fontSize = 30.sp,
                color = Color.White
            )
            Text(
                text = viewModel.data?.activity ?: "...",
                color = Color.White
            )
            ElevatedCard (
                modifier = Modifier
                    .height(100.dp),
            ){
                Column (
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ){
                    Text(text = "Participants : ${viewModel.data?.participants.toString()}")
                    Text(text = "Price : ${viewModel.data?.price.toString()}")
                    Text(text = "Accessibility : ${viewModel.data?.accessibility.toString()}")
                }
            }
            ClickableText(
                text = AnnotatedString("Link : ${strLink ?: "No Link"}"),
            ){
                localUriHandler.openUri(link ?: "")
            }
        }
        ButtonBack(navHostController = navHostController)
}