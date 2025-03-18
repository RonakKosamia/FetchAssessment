package com.example.fetchrewards.domain.repository

import com.example.fetchrewards.data.repository.Result
import com.example.fetchrewards.domain.model.FetchItem

interface FetchRepository {
    suspend fun getItems(): Result<List<FetchItem>>
}