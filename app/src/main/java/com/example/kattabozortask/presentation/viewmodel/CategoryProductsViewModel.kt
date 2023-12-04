package com.example.kattabozortask.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.example.kattabozortask.network.data.Product

interface CategoryProductsViewModel {
    val error: LiveData<String>
    val categoryProductsLiveData: LiveData<List<Product>>

    fun getCategoryProducts()
}