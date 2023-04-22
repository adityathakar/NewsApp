package com.example.domain.repository

import com.example.domain.model.Article
import com.example.domain.model.Source
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getArticles(): List<Article>

    suspend fun getSavedArticles(): Flow<List<Article>>

    suspend fun addArticleToDb(article: Article)

    suspend fun removeArticleFromDb(article: Article)

    suspend fun getSources(): Flow<List<Source>>

    suspend fun addSourceToDb(sourceId: String)

    suspend fun removeSourceFromDb(sourceId: String)

    suspend fun isArticleFavourite(article: Article) : Flow<Boolean>
}