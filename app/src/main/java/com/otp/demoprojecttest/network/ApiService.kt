package com.otp.demoprojecttest.network

import com.otp.demoprojecttest.model.Item
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiService {
    @GET("v3/db9421b8-df8d-4371-b352-517d530b765b")
        suspend fun getItems(): Response<ApiResponse>
}