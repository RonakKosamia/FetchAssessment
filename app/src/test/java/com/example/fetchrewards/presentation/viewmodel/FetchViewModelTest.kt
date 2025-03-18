package com.example.fetchrewards.presentation.viewmodel

import com.example.fetchrewards.TestCoroutineRule
import com.example.fetchrewards.data.repository.Result
import com.example.fetchrewards.domain.model.FetchItem
import com.example.fetchrewards.domain.usecase.GetSortedItemsUseCase
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.example.fetchrewards.presentation.viewmodel.FetchViewModel
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

@ExperimentalCoroutinesApi
class FetchViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule() // ✅ Fixes coroutine testing

    private lateinit var useCase: GetSortedItemsUseCase
    private lateinit var viewModel: FetchViewModel

    @Before
    fun setUp() {
        useCase = mockk() // ✅ Ensure mock is created before ViewModel
    }

    @Test
    fun `fetchItems updates state to Success when data is loaded`() = runTest {
        val mockData = listOf(FetchItem(id = 1, listId = 1, name = "Item 5"))

        // ✅ Mock BEFORE initializing the ViewModel
        coEvery { useCase.execute() } returns Result.Success(mockData)

        // ✅ Initialize ViewModel after mocking
        viewModel = FetchViewModel(useCase)

        val state = viewModel.items.first()

        assertTrue(state is Result.Success)
        assertEquals(1, (state as Result.Success).data.size)

        coVerify(atLeast = 1) { useCase.execute() } // ✅ Allows multiple calls
    }

    @Test
    fun `fetchItems updates state to Error when API fails`() = runTest {
        // ✅ Properly mock failure scenario
        coEvery { useCase.execute() } returns Result.Error("Server Failure")

        viewModel = FetchViewModel(useCase)

        val state = viewModel.items.first()

        assertTrue(state is Result.Error)
        assertEquals("Server Failure", (state as Result.Error).message)

        coVerify(atLeast = 1) { useCase.execute() } // ✅ Allows multiple calls
    }
}
