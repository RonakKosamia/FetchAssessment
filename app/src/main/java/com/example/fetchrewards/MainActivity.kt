package com.example.fetchrewards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fetchrewards.presentation.ui.FetchListScreen
import com.example.fetchrewards.presentation.viewmodel.FetchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: FetchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FetchListScreen(viewModel)
        }
    }
}