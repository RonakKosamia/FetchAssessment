package com.example.fetchrewards.di

import com.example.fetchrewards.data.remote.FetchApiService
import com.example.fetchrewards.data.repository.FetchRepositoryImpl
import com.example.fetchrewards.domain.repository.FetchRepository
import com.example.fetchrewards.domain.usecase.GetSortedItemsUseCase
import com.example.fetchrewards.presentation.viewmodel.FetchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit
            .Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FetchApiService::class.java)
    }
    single<FetchRepository> { FetchRepositoryImpl(get()) }
    single { GetSortedItemsUseCase(get()) }
    viewModel { FetchViewModel(get()) }
}