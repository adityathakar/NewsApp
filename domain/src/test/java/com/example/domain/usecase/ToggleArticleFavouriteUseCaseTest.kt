package com.example.domain.usecase

import com.example.domain.model.Article
import com.example.domain.repository.NewsRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ToggleArticleFavouriteUseCaseTest {

    private val newsRepository = mockk<NewsRepository>()
    private val article = mockk<Article>()

    private lateinit var toggleArticleFavouriteUseCase: ToggleArticleFavouriteUseCase

    @Before
    fun setup() {
        toggleArticleFavouriteUseCase = ToggleArticleFavouriteUseCase(newsRepository)
    }

    @Test
    fun `When execute called with isFavourite true, then call addArticleToDb on NewsRepository`() =
        runTest {
            coEvery { newsRepository.addArticleToDb(article) } just Runs

            toggleArticleFavouriteUseCase.execute(true, article)

            coVerify { newsRepository.addArticleToDb(article) }
        }

    @Test
    fun `When execute called with isFavourite false, then call addArticleToDb on NewsRepository`() =
        runTest {
            coEvery { newsRepository.removeArticleFromDb(article) } just Runs

            toggleArticleFavouriteUseCase.execute(false, article)

            coVerify { newsRepository.removeArticleFromDb(article) }
        }
}