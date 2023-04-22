package com.example.domain.usecase

import com.example.domain.model.Article
import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class ToggleArticleFavouriteUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend fun execute(isSelected: Boolean, article: Article) {
        if (isSelected) {
            newsRepository.addArticleToDb(article)
        } else {
            newsRepository.removeArticleFromDb(article)
        }
    }
}