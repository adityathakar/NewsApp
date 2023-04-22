package com.example.domain.usecase

import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class ToggleSourceSelectionUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend fun execute(isSelected: Boolean, sourceId: String) {
        if (isSelected) {
            newsRepository.addSourceToDb(sourceId)
        } else {
            newsRepository.removeSourceFromDb(sourceId)
        }
    }
}