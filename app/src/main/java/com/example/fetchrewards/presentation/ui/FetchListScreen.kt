package com.example.fetchrewards.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fetchrewards.data.repository.Result
import com.example.fetchrewards.domain.model.FetchItem
import com.example.fetchrewards.presentation.viewmodel.FetchViewModel

@Composable
fun FetchListScreen(viewModel: FetchViewModel = viewModel()) {
    val result by viewModel.items.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Fetch Data List") }) }
    ) {
        Column(modifier = Modifier.padding(it)) {
            when (result) {
                is Result.Success -> FetchList((result as Result.Success<List<FetchItem>>).data)
                is Result.Error -> ErrorScreen((result as Result.Error).message)
            }
        }
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Error: $message", color = Color.Red)
    }
}


@Composable
fun FetchList(items: List<FetchItem>) {
    val groupedItems = items.groupBy { it.listId }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        groupedItems.forEach { (listId, items) ->
            item {
                Text(
                    text = "List ID: $listId",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(8.dp)
                )
            }
            items(items) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    elevation = 4.dp
                ) {
                    Text(
                        text = "${item.id}: ${item.name}",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

//@Composable
//fun FetchList(items: List<FetchItem>) {
//    //Step 1: Filter out items where `name` is blank or null
//    val filteredItems = items.filter { it.name.isNotBlank() }
//    //Step 2: Sort first by `listId`, then by `name`
//    val sortedItems = filteredItems.sortedWith(compareBy({ it.listId }, { it.name }))
//    //Step 3: Group by `listId`
//    val groupedItems = sortedItems.groupBy { it.listId }
//    println("GroupedItems: $groupedItems")
//    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//        groupedItems.forEach { (listId, items) ->
//            item {
//                Text(
//                    text = "List ID: $listId",
//                    style = MaterialTheme.typography.h6,
//                    modifier = Modifier.padding(8.dp)
//                )
//            }
//            items(items) { item ->
//                Card(
//                    modifier = Modifier.fillMaxWidth().padding(4.dp),
//                    elevation = 4.dp
//                ) {
//                    Text(
//                        text = "${item.id}: ${item.name}",
//                        modifier = Modifier.padding(16.dp)
//                    )
//                }
//            }
//        }
//    }
//}

