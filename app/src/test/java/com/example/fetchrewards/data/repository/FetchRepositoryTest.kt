package com.example.fetchrewards.data.repository

import com.example.fetchrewards.data.model.FetchItemDto
import com.example.fetchrewards.data.remote.FetchApiService
import com.example.fetchrewards.domain.model.FetchItem
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class FetchRepositoryTest {
    private lateinit var apiService: FetchApiService
    private lateinit var repository: FetchRepositoryImpl

    @Before
    fun setUp() {
        apiService = mockk()
        repository = FetchRepositoryImpl(apiService)
    }

    @Test
    fun `getItems returns success when API is successful`() = runBlocking {
        // Mock API response
        val mockResponse = listOf(
            FetchItemDto(id = 1, listId = 1, name = "Item 10"),
            FetchItemDto(id = 2, listId = 1, name = "Item 2")
        )
        coEvery { apiService.getItems() } returns Response.success(mockResponse)

        val result = repository.getItems()

        assertTrue(result is Result.Success)
        assertEquals(2, (result as Result.Success).data.size)
    }

    @Test
    fun `getItems retries on failure up to max 3 times`() = runBlocking {
        // Mock API to return error response
        coEvery { apiService.getItems() } returns Response.error(500, mockk(relaxed = true))

        val result = repository.getItems()

        assertTrue(result is Result.Error)
        assertEquals("Failed to load data after 3 attempts.", (result as Result.Error).message)

        coVerify(exactly = 3) { apiService.getItems() } // Ensure it retried 3 times
    }
}
