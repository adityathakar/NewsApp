package com.example.domain.repository

import com.example.domain.model.Article
import com.example.domain.model.Source
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getArticles(): List<Article>

    suspend fun getSources(): Flow<List<Source>>

    suspend fun addSourceToDb(sourceId: String)

    suspend fun removeSourceFromDb(sourceId: String)
}