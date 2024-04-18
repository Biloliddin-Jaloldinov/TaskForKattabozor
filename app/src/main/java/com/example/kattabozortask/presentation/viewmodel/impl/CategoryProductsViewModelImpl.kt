package com.example.kattabozortask.presentation.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kattabozortask.domain.Repository
import com.example.kattabozortask.network.data.Product
import com.example.kattabozortask.presentation.viewmodel.CategoryProductsViewModel
import com.example.kattabozortask.utils.isConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryProductsViewModelImpl : CategoryProductsViewModel, ViewModel() {

    override val categoryProductsLiveData: LiveData<List<Product>> get() = _categoryOffers

    private val _categoryOffers = MutableLiveData<List<Product>>()
    private val _error = MutableLiveData<String>()

    override val error: LiveData<String> get() = _error
    private val repository = Repository.getInstance()

    override fun getCategoryProducts() {
        if (!isConnected()) {
            _error.value = "Internet not connected"
            return
        }
        viewModelScope.launch{
            val response = repository.getCategoryProducts()

            if (response != null && response.isSuccessful) {
                _categoryOffers.value = response.body()?.offers
            } else {
                _error.value = "Failed to fetch data"
            }
        }
    }
}