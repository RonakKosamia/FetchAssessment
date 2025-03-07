package com.example.fetchrewards.data.repository

import com.example.fetchrewards.data.remote.FetchApiService
import com.example.fetchrewards.domain.model.FetchItem
import com.example.fetchrewards.domain.repository.FetchRepository

class FetchRepositoryImpl(private val apiService: FetchApiService) : FetchRepository {
    override suspend fun getItems(): List<FetchItem> {
        val response = apiService.getItems()
        return if (response.isSuccessful) {
            response.body()?.mapNotNull { dto ->
                dto.name?.takeIf { it.isNotBlank() }?.let {
                    FetchItem(id = dto.id, listId = dto.listId, name = it)
                }
            }?.sortedWith(compareBy({ it.listId }, { it.name })) ?: emptyList()
        } else {
            emptyList()
        }
    }
}