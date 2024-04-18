package com.example.kattabozortask.network

import com.example.kattabozortask.network.data.CategoryProducts
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("hh/test/api/v1/offers")
    suspend fun getCategoryProducts(): Response<CategoryProducts>

}