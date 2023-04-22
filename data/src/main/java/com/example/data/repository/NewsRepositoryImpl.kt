package com.example.data.repository

import com.example.data.api.service.NewsApiService
import com.example.data.db.dao.SourceDao
import com.example.data.db.entity.SourceEntity
import com.example.domain.model.Article
import com.example.domain.model.Source
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    private val sourceDao: SourceDao,
) : NewsRepository {

    override suspend fun getArticles(): List<Article> {
        val sources = sourceDao.getSources().take(1).toList()
        val sourcesString = sources[0].joinToString { it.sourceId }

        return newsApiService.getArticles(sourcesString).map {
            Article(
                author = it.author ?: "",
                title = it.title ?: "",
                description = it.description ?: "",
                articleUrl = it.articleUrl,
                articleImageUrl = it.articleImageUrl ?: ""
            )
        }
    }

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
}