package com.example.newsapp.ui.screens.articledetail

import com.example.domain.model.Article
import com.example.domain.usecase.IsArticleFavouriteUseCase
import com.example.domain.usecase.ToggleArticleFavouriteUseCase
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ArticleDetailViewModelTest {

    private val toggleArticleFavouriteUseCase = mockk<ToggleArticleFavouriteUseCase>()
    private val isArticleFavouriteUseCase = mockk<IsArticleFavouriteUseCase>()

    private lateinit var articleDetailViewModel: ArticleDetailViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        articleDetailViewModel =
            ArticleDetailViewModel(toggleArticleFavouriteUseCase, isArticleFavouriteUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When ArticleDetailViewModel created, then return NotFavourite state`() = runTest {
        val stateValue = articleDetailViewModel.state.value

        Assert.assertEquals(ArticleDetailState.NotFavourite, stateValue)
    }

    @Test
    fun `Given IsArticleFavouriteUseCase return true, when isArticleFavourite created, then return Favourite state`() =
        runTest {
            val article = Article(
                author = "author",
                title = "title",
                description = "description",
                articleUrl = "articleUrl",
                articleImageUrl = "articleImageUrl"
            )
            coEvery { isArticleFavouriteUseCase.execute(article) } returns flowOf(true)

            articleDetailViewModel.isArticleFavourite(article)

            advanceTimeBy(1000)
            val stateValue = articleDetailViewModel.state.value
            Assert.assertEquals(ArticleDetailState.Favourite, stateValue)
        }

    @Test
    fun `Given IsArticleFavouriteUseCase return false, when isArticleFavourite created, then return NotFavourite state`() =
        runTest {
            val article = Article(
                author = "author",
                title = "title",
                description = "description",
                articleUrl = "articleUrl",
                articleImageUrl = "articleImageUrl"
            )
            coEvery { isArticleFavouriteUseCase.execute(article) } returns flowOf(false)

            articleDetailViewModel.isArticleFavourite(article)

            advanceTimeBy(1000)
            val stateValue = articleDetailViewModel.state.value
            Assert.assertEquals(ArticleDetailState.NotFavourite, stateValue)
        }

    @Test
    fun `When toggleArticleFavourite is called, then call ToggleArticleFavouriteUseCase`() =
        runTest {
            val article = Article(
                author = "author",
                title = "title",
                description = "description",
                articleUrl = "articleUrl",
                articleImageUrl = "articleImageUrl"
            )
            coEvery { toggleArticleFavouriteUseCase.execute(true, article) } just Runs

            articleDetailViewModel.toggleArticleFavourite(true, article)

            advanceTimeBy(1000)
            coVerify { toggleArticleFavouriteUseCase.execute(true, article) }
        }
}