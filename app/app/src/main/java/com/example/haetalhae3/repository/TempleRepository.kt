package com.example.haetalhae3.repository

import com.example.haetalhae3.data.api.TempleApi
import com.example.haetalhae3.data.model.Temple
import retrofit2.Call

class TempleRepository(private val templeApi: TempleApi) {

    fun getTemples(): Call<List<Temple>> {
        return templeApi.getTemples()
    }
}
