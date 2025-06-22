package com.example.haetalhae3.data.model

data class Temple(
    val id: Long = 0,
    val name: String,
    val address: String,
    val history: String,
    val imageUrl: String,
    val latitude: Double?,
    val longitude: Double?
)