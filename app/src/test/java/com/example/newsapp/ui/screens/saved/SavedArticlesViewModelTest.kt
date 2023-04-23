package com.example.newsapp.ui.screens.saved

import com.example.domain.model.Article
import com.example.domain.usecase.GetSavedArticlesUseCase
import io.mockk.coEvery
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
class SavedArticlesViewModelTest {

    private val getSavedArticlesUseCase = mockk<GetSavedArticlesUseCase>()

    private lateinit var savedArticlesViewModel: SavedArticlesViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        savedArticlesViewModel = SavedArticlesViewModel(getSavedArticlesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When SavedArticlesViewModel created, then return Loading state`() = runTest {
        val stateValue = savedArticlesViewModel.state.value

        Assert.assertEquals(SavedArticlesState.Loading, stateValue)
    }

    @Test
    fun `Given GetSavedArticlesUseCase return error, when SavedArticlesViewModel created, then return Error state`() =
        runTest {
            coEvery { getSavedArticlesUseCase.execute() } throws Exception()

            advanceTimeBy(1000)
            val stateValue = savedArticlesViewModel.state.value
            Assert.assertEquals(SavedArticlesState.Error, stateValue)
        }

    @Test
    fun `Given GetSavedArticlesUseCase return empty list, when SavedArticlesViewModel created, then return Empty state`() =
        runTest {
            coEvery { getSavedArticlesUseCase.execute() } returns flowOf(emptyList())

            advanceTimeBy(1000)
            val stateValue = savedArticlesViewModel.state.value
            Assert.assertEquals(SavedArticlesState.Empty, stateValue)
        }

    @Test
    fun `Given GetSavedArticlesUseCase return non-empty list, when SavedArticlesViewModel created, then return Success state with list`() =
        runTest {
            val article = Article(
                author = "author",
                title = "title",
                description = "description",
                articleUrl = "articleUrl",
                articleImageUrl = "articleImageUrl"
            )

            coEvery { getSavedArticlesUseCase.execute() } returns flowOf(listOf(article))

            advanceTimeBy(1000)
            val stateValue = savedArticlesViewModel.state.value
            Assert.assertEquals(SavedArticlesState.Success(listOf(article)), stateValue)
        }
}