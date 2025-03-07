package com.example.fetchrewards.data.remote

import com.example.fetchrewards.data.model.FetchItemDto
import retrofit2.Response
import retrofit2.http.GET

interface FetchApiService {
    @GET("hiring.json")
    suspend fun getItems(): Response<List<FetchItemDto>>
}