package com.example.fetchrewards.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchrewards.domain.model.FetchItem
import com.example.fetchrewards.domain.usecase.GetSortedItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.fetchrewards.data.repository.Result

class FetchViewModel(private val getSortedItemsUseCase: GetSortedItemsUseCase) : ViewModel() {
    private val _items = MutableStateFlow<Result<List<FetchItem>>>(Result.Success(emptyList()))
    val items: StateFlow<Result<List<FetchItem>>> = _items.asStateFlow()

    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            _items.value = Result.Error("Loading...") // Show loading state
            val fetchedItems = getSortedItemsUseCase.execute()
            _items.value = fetchedItems
        }
    }
}

// ✅ Extract numeric values from name
//private fun extractNumber(name: String): Int {
//    return name.filter { it.isDigit() }.toIntOrNull() ?: Int.MAX_VALUE
//}

fun extractNumber(name: String): Int {
    val numberPart = name.filter { it.isDigit() }
    //Int.MAX_VALUE used Defaults to large number if no digits
    return numberPart.toIntOrNull() ?: Int.MAX_VALUE // Defaults to max if no number exists
}

