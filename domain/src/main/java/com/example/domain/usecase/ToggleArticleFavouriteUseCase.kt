package com.example.domain.usecase

import com.example.domain.model.Article
import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class ToggleArticleFavouriteUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend fun execute(isFavourite: Boolean, article: Article) {
        if (isFavourite) {
            newsRepository.addArticleToDb(article)
        } else {
            newsRepository.removeArticleFromDb(article)
        }
    }
}