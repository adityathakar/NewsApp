package com.example.domain.usecase

import com.example.domain.repository.SourceRepository
import javax.inject.Inject

class ToggleSourceSelectionUseCase @Inject constructor(
    private val sourceRepository: SourceRepository
) {

    suspend fun execute(isSelected: Boolean, sourceId: String) {
        if (isSelected) {
            sourceRepository.addSourceToDb(sourceId)
        } else {
            sourceRepository.removeSourceFromDb(sourceId)
        }
    }
}