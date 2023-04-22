package com.example.domain.usecase

import com.example.domain.model.Source
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend fun execute(): Flow<List<Source>> = newsRepository.getSources()
}