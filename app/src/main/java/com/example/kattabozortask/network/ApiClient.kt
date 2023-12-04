package com.example.kattabozortask.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.kattabozortask.app.App
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val myClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor.Builder(App.context).build())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://kattabozor.uz")
        .client(myClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun categoryApi(): Api = retrofit.create(Api::class.java)


}



