package com.example.data.api.service

import com.example.data.model.SourceApiModel

interface NewsApiService {

    suspend fun getSources() : List<SourceApiModel>
}