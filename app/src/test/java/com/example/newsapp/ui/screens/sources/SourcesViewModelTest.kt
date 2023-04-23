package com.example.newsapp.ui.screens.sources

import com.example.domain.model.Source
import com.example.domain.usecase.GetSourcesUseCase
import com.example.domain.usecase.ToggleSourceSelectionUseCase
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
class SourcesViewModelTest {

    private val getSourcesUseCase = mockk<GetSourcesUseCase>()
    private val toggleSourceSelectionUseCase = mockk<ToggleSourceSelectionUseCase>()

    private lateinit var sourcesViewModel: SourcesViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        sourcesViewModel = SourcesViewModel(getSourcesUseCase, toggleSourceSelectionUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When SourcesViewModel created, then return Loading state`() = runTest {
        val stateValue = sourcesViewModel.state.value

        Assert.assertEquals(SourcesState.Loading, stateValue)
    }

    @Test
    fun `Given GetSourcesUseCase return non-empty list, when SourcesViewModel created, then return Success state with list`() =
        runTest {
            val source = Source(
                id = "id",
                name = "name",
                isSelected = false
            )

            coEvery { getSourcesUseCase.execute() } returns flowOf(listOf(source))

            advanceTimeBy(1000)
            val stateValue = sourcesViewModel.state.value
            Assert.assertEquals(SourcesState.Success(listOf(source)), stateValue)
        }

    @Test
    fun `Given GetSourcesUseCase return empty list, when SourcesViewModel created, then return Empty state`() =
        runTest {
            coEvery { getSourcesUseCase.execute() } returns flowOf(emptyList())

            advanceTimeBy(1000)
            val stateValue = sourcesViewModel.state.value
            Assert.assertEquals(SourcesState.Empty, stateValue)
        }

    @Test
    fun `Given GetSourcesUseCase return error, when SourcesViewModel created, then return Error state`() =
        runTest {
            coEvery { getSourcesUseCase.execute() } throws Exception()

            advanceTimeBy(1000)
            val stateValue = sourcesViewModel.state.value
            Assert.assertEquals(SourcesState.Error, stateValue)
        }

    @Test
    fun `When toggleSourceSelection is called, then call ToggleSourceSelectionUseCase`() = runTest {
        coEvery { toggleSourceSelectionUseCase.execute(true, "sourceId") } just Runs

        sourcesViewModel.toggleSourceSelection(true, "sourceId")

        advanceTimeBy(1000)
        coVerify { toggleSourceSelectionUseCase.execute(true, "sourceId") }
    }
}