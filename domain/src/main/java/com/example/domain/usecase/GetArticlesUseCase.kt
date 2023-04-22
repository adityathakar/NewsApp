package com.example.domain.usecase

import com.example.domain.model.Article
import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend fun execute() : List<Article> = newsRepository.getArticles()
}