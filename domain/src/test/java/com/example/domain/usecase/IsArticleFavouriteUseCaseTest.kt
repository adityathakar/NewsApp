package com.example.domain.usecase

import com.example.domain.model.Article
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
class IsArticleFavouriteUseCaseTest {

    private val newsRepository = mockk<NewsRepository>()

    private lateinit var isArticleFavouriteUseCase: IsArticleFavouriteUseCase

    @Before
    fun setup() {
        isArticleFavouriteUseCase = IsArticleFavouriteUseCase(newsRepository)
    }

    @Test
    fun `When execute is called, then call isArticleFavourite on NewsRepository`() = runTest {
        val article = Article(
            author = "author",
            title = "title",
            description = "description",
            articleUrl = "articleUrl",
            articleImageUrl = "imageUrl"
        )
        coEvery { newsRepository.isArticleFavourite(article) } returns flowOf(true)

        isArticleFavouriteUseCase.execute(article)

        coVerify { newsRepository.isArticleFavourite(article) }
    }

}