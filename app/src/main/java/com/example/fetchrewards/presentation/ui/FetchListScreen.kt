package com.example.fetchrewards.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fetchrewards.domain.model.FetchItem
import com.example.fetchrewards.presentation.viewmodel.FetchViewModel

@Composable
fun FetchListScreen(viewModel: FetchViewModel = viewModel()) {
    val items by viewModel.items.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Fetch Data List") }) }
    ) {
        Column(modifier = Modifier.padding(it)) {
            FetchList(items)
        }
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

