package com.example.data.repository

import com.example.data.api.service.NewsApiService
import com.example.data.db.dao.ArticleDao
import com.example.data.db.dao.SourceDao
import com.example.data.db.entity.ArticleEntity
import com.example.data.db.entity.SourceEntity
import com.example.domain.model.Article
import com.example.domain.model.Source
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    private val sourceDao: SourceDao,
    private val articleDao: ArticleDao,
) : NewsRepository {

    override suspend fun getArticles(): List<Article> {
        val sources = sourceDao.getSources().firstOrNull()
        val sourcesString = sources?.joinToString { it.sourceId }

        return if (sourcesString.isNullOrBlank()) {
            emptyList()
        } else {
            newsApiService.getArticles(sourcesString).map {
                Article(
                    author = it.author ?: "",
                    title = it.title ?: "",
                    description = it.description ?: "",
                    articleUrl = it.articleUrl,
                    articleImageUrl = it.articleImageUrl ?: ""
                )
            }
        }
    }

    override suspend fun getSavedArticles(): Flow<List<Article>> {
        return articleDao.getArticles().map { articleEntityList ->
            articleEntityList.map {
                Article(
                    author = it.author,
                    title = it.title,
                    description = it.description,
                    articleUrl = it.url,
                    articleImageUrl = it.imageUrl,
                )
            }
        }
    }

    override suspend fun addArticleToDb(article: Article) =
        articleDao.addArticle(
            ArticleEntity(
                url = article.articleUrl,
                imageUrl = article.articleImageUrl,
                author = article.author,
                title = article.title,
                description = article.description,
            )
        )


    override suspend fun removeArticleFromDb(article: Article) =
        articleDao.removeArticle(
            ArticleEntity(
                url = article.articleUrl,
                imageUrl = article.articleImageUrl,
                author = article.author,
                title = article.title,
                description = article.description,
            )
        )

    override suspend fun getSources(): Flow<List<Source>> {
        return combine(
            flowOf(newsApiService.getSources()),
            sourceDao.getSources()
        ) { apiSources, dbSources ->
            val transformedList = mutableListOf<Source>()
            apiSources.forEach { sourceApiModel ->
                transformedList.add(
                    Source(
                        id = sourceApiModel.id,
                        name = sourceApiModel.name,
                        isSelected = dbSources.any { sourceEntity -> sourceEntity.sourceId == sourceApiModel.id }
                    ))
            }

            transformedList
        }
    }

    override suspend fun addSourceToDb(sourceId: String) =
        sourceDao.addSource(SourceEntity(sourceId = sourceId))

    override suspend fun removeSourceFromDb(sourceId: String) =
        sourceDao.removeSource(SourceEntity(sourceId = sourceId))

    override suspend fun isArticleFavourite(article: Article): Flow<Boolean> {
        return articleDao.articleExist(article.articleUrl)
    }
}