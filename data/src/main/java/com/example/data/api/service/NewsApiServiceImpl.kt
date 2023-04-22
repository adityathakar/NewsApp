package com.example.data.api.service

import com.example.data.api.NewsApi
import com.example.data.model.ArticleApiModel
import com.example.data.model.SourceApiModel
import javax.inject.Inject

class NewsApiServiceImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsApiService {

    override suspend fun getArticles(sources: String): List<ArticleApiModel> =
        newsApi.getArticles(sources = sources).articles

    override suspend fun getSources(): List<SourceApiModel> = newsApi.getSources().sources

}