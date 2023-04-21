package com.example.domain.repository

import com.example.domain.model.Source

interface SourceRepository {

    suspend fun getSources() : List<Source>

    fun addSourceToDb(source: Source)

    fun removeSourceFromDb(source: Source)
}