package com.example.newsapp.ui.screens.headlines

import com.example.domain.model.Article
import com.example.domain.usecase.GetArticlesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class ArticlesViewModelTest {

    private val getArticlesUseCase = mockk<GetArticlesUseCase>()

    private lateinit var articlesViewModel: ArticlesViewModel


    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        articlesViewModel = ArticlesViewModel(getArticlesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When ArticlesViewModel created, then return Loading state`() = runTest {
        val stateValue = articlesViewModel.state.value

        Assert.assertEquals(ArticlesState.Loading, stateValue)
    }

    @Test
    fun `Given GetArticlesUseCase returns non-empty list, when ArticlesViewModel created, then return Success state with list`() =
        runTest {
            val article = Article(
                author = "author",
                title = "title",
                description = "description",
                articleUrl = "articleUrl",
                articleImageUrl = "articleImageUrl"
            )
            coEvery { getArticlesUseCase.execute() } returns listOf(article)

            advanceTimeBy(1000)
            val stateValue = articlesViewModel.state.value

            Assert.assertEquals(ArticlesState.Success(listOf(article)), stateValue)
        }

    @Test
    fun `Given GetArticlesUseCase returns empty list, when ArticlesViewModel created, then return Empty state`() =
        runTest {
            coEvery { getArticlesUseCase.execute() } returns emptyList()

            advanceTimeBy(1000)
            val stateValue = articlesViewModel.state.value

            Assert.assertEquals(ArticlesState.Empty, stateValue)
        }

    @Test
    fun `Given GetArticlesUseCase returns error, when ArticlesViewModel created, then return Error state`() =
        runTest {
            coEvery { getArticlesUseCase.execute() } throws Exception()

            advanceTimeBy(1000)
            val stateValue = articlesViewModel.state.value

            Assert.assertEquals(ArticlesState.Error, stateValue)
        }
}