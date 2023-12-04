package com.example.kattabozortask.network.data

data class Product(
    val  id: Int,
    val name: String,
    val brand: String,
    val category: String,
    val merchant: String,
    val attributes: List<AttributeDto>,
    val image: ImageDto
)
