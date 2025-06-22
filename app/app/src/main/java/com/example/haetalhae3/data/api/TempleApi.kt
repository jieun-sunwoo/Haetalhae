package com.example.haetalhae3.data.api

import com.example.haetalhae3.data.model.Temple
import retrofit2.Call
import retrofit2.http.GET

interface TempleApi {
    @GET("temples")
    fun getTemples(): Call<List<Temple>>
}
