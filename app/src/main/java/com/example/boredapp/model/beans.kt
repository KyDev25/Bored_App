package com.example.boredapp.model

import com.example.boredapp.R

fun returnImage(typeName: String) : Int{
       if(typeName == "busywork"){
           return R.drawable.busywork
       }
       if(typeName == "charity"){
           return R.drawable.charity
       }
       if(typeName == "cooking"){
           return R.drawable.cooking
       }
       if(typeName == "diy"){
           return R.drawable.diy
       }
       if(typeName == "education"){
           return R.drawable.education
       }
       if(typeName == "music"){
           return R.drawable.music
       }
       if(typeName == "recreational"){
           return R.drawable.recreational
       }
       if(typeName == "relaxation"){
           return R.drawable.relaxation
       }
       if(typeName == "social"){
           return R.drawable.social
       }
       return R.drawable.default_png
   }
