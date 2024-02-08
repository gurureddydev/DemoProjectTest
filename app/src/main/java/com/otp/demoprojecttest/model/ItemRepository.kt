package com.otp.demoprojecttest.model

import com.otp.demoprojecttest.network.ApiResponse
import com.otp.demoprojecttest.network.ApiService
import retrofit2.Response

class ItemRepository(private val apiService: ApiService) {
    suspend fun getItems(): Response<ApiResponse> {
        return apiService.getItems()
    }
}




