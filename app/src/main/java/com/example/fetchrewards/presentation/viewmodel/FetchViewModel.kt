package com.example.fetchrewards.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchrewards.domain.model.FetchItem
import com.example.fetchrewards.domain.usecase.GetSortedItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FetchViewModel(private val getSortedItemsUseCase: GetSortedItemsUseCase) : ViewModel() {
    private val _items = MutableStateFlow<List<FetchItem>>(emptyList())
    val items: StateFlow<List<FetchItem>> = _items.asStateFlow()

    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            val fetchedItems = getSortedItemsUseCase.execute()
                .filter { !it.name.isNullOrBlank() }
//                .sortedWith(compareBy({ it.listId }, { it.name })) //TODO: if you want lexicographic sorting.
                .sortedWith(compareBy({ it.listId }, { it.name?.let { it1 -> extractNumber(it1) } })) //non- lexicographic sorting.

            println("ViewModel fetched ${fetchedItems.size} items")
            _items.value = fetchedItems
        }
    }
}

// ✅ Extract numeric values from name
private fun extractNumber(name: String): Int {
    return name.filter { it.isDigit() }.toIntOrNull() ?: Int.MAX_VALUE // Defaults to large number if no digits
}

