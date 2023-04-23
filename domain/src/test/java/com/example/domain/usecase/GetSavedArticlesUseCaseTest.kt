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
class GetSavedArticlesUseCaseTest {

    private val newsRepository = mockk<NewsRepository>()

    private lateinit var getSavedArticlesUseCase: GetSavedArticlesUseCase

    @Before
    fun setup() {
        getSavedArticlesUseCase = GetSavedArticlesUseCase(newsRepository)
    }

    @Test
    fun `When execute called, then call getSavedArticles on NewsRepository`() = runTest {
        coEvery { newsRepository.getSavedArticles() } returns flowOf(emptyList())

        getSavedArticlesUseCase.execute()

        coVerify { newsRepository.getSavedArticles() }
    }
}