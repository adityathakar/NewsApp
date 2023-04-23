package com.example.domain.usecase

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
class ToggleSourceSelectionUseCaseTest {

    private val newsRepository = mockk<NewsRepository>()
    private val sourceId = "sourceId"

    private lateinit var toggleSourceSelectionUseCase: ToggleSourceSelectionUseCase

    @Before
    fun setup() {
        toggleSourceSelectionUseCase = ToggleSourceSelectionUseCase(newsRepository)
    }

    @Test
    fun `When execute called with isSelected true, then call addSourceToDb on NewsRepository`() =
        runTest {
            coEvery { newsRepository.addSourceToDb(sourceId) } just Runs

            toggleSourceSelectionUseCase.execute(true, sourceId)

            coVerify { newsRepository.addSourceToDb(sourceId) }
        }

    @Test
    fun `When execute called with isSelected false, then call removeSourceFromDb on NewsRepository`() =
        runTest {
            coEvery { newsRepository.removeSourceFromDb(sourceId) } just Runs

            toggleSourceSelectionUseCase.execute(false, sourceId)

            coVerify { newsRepository.removeSourceFromDb(sourceId) }
        }
}