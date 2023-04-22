package com.example.domain.usecase

import com.example.domain.model.Source
import com.example.domain.repository.SourceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(
    private val sourceRepository: SourceRepository
) {

    suspend fun execute(): Flow<List<Source>> = sourceRepository.getSources()
}