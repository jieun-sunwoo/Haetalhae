package com.example.haetalhae3.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8080/"  // 너의 백엔드 주소로 바꿔!

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)  // 서버 주소
            .addConverterFactory(GsonConverterFactory.create())  // JSON 변환기 등록
            .build()
    }
}
