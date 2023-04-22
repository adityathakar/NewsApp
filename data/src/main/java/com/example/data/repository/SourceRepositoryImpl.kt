package com.example.data.repository

import com.example.data.api.service.NewsApiService
import com.example.data.db.dao.SourceDao
import com.example.data.db.entity.SourceEntity
import com.example.domain.model.Source
import com.example.domain.repository.SourceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SourceRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    private val sourceDao: SourceDao,
) : SourceRepository {

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