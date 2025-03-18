package com.example.fetchrewards.data.repository

import com.example.fetchrewards.data.remote.FetchApiService
import com.example.fetchrewards.domain.model.FetchItem
import com.example.fetchrewards.domain.repository.FetchRepository
import kotlinx.coroutines.delay

class FetchRepositoryImpl(private val apiService: FetchApiService) : FetchRepository {

    override suspend fun getItems(): Result<List<FetchItem>> {
        var attempt = 0
        // Initially we will start with a delay
        var delayTime = 1000L
        //maximum number of retries that will be allowed
        val maxRetries = 3

        while (attempt < maxRetries) {
            val response = apiService.getItems()
            if (response.isSuccessful) {
                val items = response.body()?.mapNotNull { dto ->
                    dto.name?.takeIf { it.isNotBlank() }?.let {
                        FetchItem(id = dto.id, listId = dto.listId, name = it)
                    }
                }?.sortedWith(compareBy({ it.listId }, { it.name })) ?: emptyList()
                //.sortedWith(compareBy({ it.listId }, { extractNumber(it.name) })) ?: emptyList()
                return Result.Success(items)
            } else {
                attempt++
                if (attempt == maxRetries) {
                    return Result.Error("Failed to load data after $maxRetries attempts.")
                }
                delay(delayTime)
                delayTime *= 2 // Exponential backoff (1s → 2s → 4s)
            }
        }
        return Result.Error("Unexpected error occurred.")
    }
}
