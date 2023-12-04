package com.example.kattabozortask.domain

import com.example.kattabozortask.network.ApiClient
import com.example.kattabozortask.network.data.CategoryProducts
import retrofit2.Response

class Repository {


    private val categoryApi = ApiClient.categoryApi()

    companion object {
        private var repository: Repository? = null

        fun getInstance(): Repository {
            if (repository == null) {
                repository = Repository()
            }
            return repository!!
        }
    }

    suspend fun getCategoryProducts(): Response<CategoryProducts>? {
        return try {
            val response = categoryApi.getCategoryOffers()
            if (response.isSuccessful) {
                response
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}


