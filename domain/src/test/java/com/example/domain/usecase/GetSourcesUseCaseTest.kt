package com.example.domain.usecase

import com.example.domain.repository.NewsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetSourcesUseCaseTest {

    private val newsRepository = mockk<NewsRepository>()

    private lateinit var getSourcesUseCase: GetSourcesUseCase

    @Before
    fun setup() {
        getSourcesUseCase = GetSourcesUseCase(newsRepository)
    }

    @Test
    fun `When execute called, then call getSources on NewsRepository`() = runTest {
        coEvery { newsRepository.getSources() } returns flowOf(emptyList())

        getSourcesUseCase.execute()

        coVerify { newsRepository.getSources() }
    }
}