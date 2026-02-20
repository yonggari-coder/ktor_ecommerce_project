package com.example.com.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val itemUrl: String,
    val name: String,
    val price: Double,
    val stock: Int,
    val description: String,
    val imageUrl: String
)