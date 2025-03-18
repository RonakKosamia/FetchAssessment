package com.example.fetchrewards.domain.usecase

import com.example.fetchrewards.data.repository.Result
import com.example.fetchrewards.domain.model.FetchItem
import com.example.fetchrewards.domain.repository.FetchRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetSortedItemsUseCaseTest {
    private lateinit var repository: FetchRepository
    private lateinit var useCase: GetSortedItemsUseCase

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetSortedItemsUseCase(repository)
    }

    @Test
    fun `execute returns success when repository succeeds`() = runBlocking {
        val mockData = listOf(FetchItem(id = 1, listId = 1, name = "Item 3"))
        coEvery { repository.getItems() } returns Result.Success(mockData)

        val result = useCase.execute()

        assertTrue(result is Result.Success)
        assertEquals(1, (result as Result.Success).data.size)
    }

    @Test
    fun `execute returns error when repository fails`() = runBlocking {
        coEvery { repository.getItems() } returns Result.Error("Network Error")

        val result = useCase.execute()

        assertTrue(result is Result.Error)
        assertEquals("Network Error", (result as Result.Error).message)
    }
}
