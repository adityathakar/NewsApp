package com.example.data.repository

import com.example.data.api.service.NewsApiService
import com.example.data.mapper.InboundSourceMapper
import com.example.domain.model.Source
import com.example.domain.repository.SourceRepository
import javax.inject.Inject

class SourceRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    private val inboundSourceMapper: InboundSourceMapper
) : SourceRepository {

    override suspend fun getSources(): List<Source> {
        return newsApiService.getSources().map {
            inboundSourceMapper.transform(it)
        }
    }

    override fun addSourceToDb(source: Source) {
        TODO("Not yet implemented")
    }

    override fun removeSourceFromDb(source: Source) {
        TODO("Not yet implemented")
    }
}