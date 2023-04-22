package com.example.domain.repository

import com.example.domain.model.Source
import kotlinx.coroutines.flow.Flow

interface SourceRepository {

    suspend fun getSources() : Flow<List<Source>>

    suspend fun addSourceToDb(sourceId: String)

    suspend fun removeSourceFromDb(sourceId: String)
}