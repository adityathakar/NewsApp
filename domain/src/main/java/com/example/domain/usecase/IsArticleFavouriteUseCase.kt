package com.example.domain.usecase

import com.example.domain.model.Article
import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class IsArticleFavouriteUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend fun execute(article: Article) = newsRepository.isArticleFavourite(article)
}