package com.example.domain.usecase

import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class GetSavedArticlesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend fun execute() = newsRepository.getSavedArticles()
}