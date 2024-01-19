package com.example.boredapp.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

object BoredAPI {
    val gson = Gson()
    val client = OkHttpClient()

    fun loadActivity(): BoredBean {

        val json: String = sendGet("https://www.boredapi.com/api/activity/")
        val data : BoredBean = gson.fromJson(json, BoredBean::class.java)

        return data
    }

    fun sendGet(url: String): String {
        println("url : $url")

        val request = Request.Builder().url(url).build()

        return client.newCall(request).execute().use {
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            it.body.string()
        }
    }
}