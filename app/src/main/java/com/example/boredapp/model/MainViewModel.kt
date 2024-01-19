package com.example.boredapp.model
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var data by mutableStateOf<BoredBean?>(null)
    var searchText by mutableStateOf("")
    var favoriteIcon by mutableStateOf(false)
    private var myFavoritesActivitiesList = mutableStateListOf<BoredBean?>()
    val myList : List<BoredBean?> = myFavoritesActivitiesList

    fun loadData() {
        data = null
        viewModelScope.launch(Dispatchers.Default) {
            data = BoredAPI.loadActivity()
        }
    }

    fun addFavorite() {
        myFavoritesActivitiesList.add(data)
        favoriteIcon = !favoriteIcon
    }

    fun removeFavorite(activity: BoredBean?) {
        myFavoritesActivitiesList.remove(activity)
        favoriteIcon = !favoriteIcon
    }

    fun uploadSearchText(newText:String){
        searchText = newText
    }
}