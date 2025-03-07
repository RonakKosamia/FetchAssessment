package com.example.fetchrewards.domain.usecase

import com.example.fetchrewards.domain.model.FetchItem
import com.example.fetchrewards.domain.repository.FetchRepository

class GetSortedItemsUseCase(private val repository: FetchRepository) {
    suspend fun execute(): List<FetchItem> {
        return repository.getItems()
    }
}