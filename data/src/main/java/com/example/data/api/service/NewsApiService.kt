package com.example.data.api.service

import com.example.data.model.ArticleApiModel
import com.example.data.model.SourceApiModel

interface NewsApiService {

    suspend fun getArticles(sources: String) : List<ArticleApiModel>

    suspend fun getSources() : List<SourceApiModel>
}