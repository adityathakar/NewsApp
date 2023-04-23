package com.example.domain.usecase

import com.example.domain.repository.NewsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetArticlesUseCaseTest {

    private val newsRepository = mockk<NewsRepository>()

    private lateinit var getArticlesUseCase: GetArticlesUseCase

    @Before
    fun setup() {
        getArticlesUseCase = GetArticlesUseCase(newsRepository)
    }

    @Test
    fun `When execute called, then call getArticles on NewsRepository`() = runTest {
        coEvery { newsRepository.getArticles() } returns emptyList()

        getArticlesUseCase.execute()

        coVerify { newsRepository.getArticles() }
    }
}