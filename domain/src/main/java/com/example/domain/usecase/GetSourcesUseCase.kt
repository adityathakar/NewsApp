package com.example.domain.usecase

import com.example.domain.model.Source
import com.example.domain.repository.SourceRepository
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(
    private val sourceRepository: SourceRepository
) {

    suspend fun execute(): List<Source> = sourceRepository.getSources()
}