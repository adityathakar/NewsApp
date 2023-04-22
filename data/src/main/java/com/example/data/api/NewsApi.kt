package com.example.data.api

import com.example.data.model.ArticlesResponse
import com.example.data.model.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getArticles(
        @Query("apiKey") apiKey: String = "0444c3bd0e754f77af95cda042d986eb",
        @Query("sources") sources: String
    ): ArticlesResponse

    @GET("top-headlines/sources?language=en&apiKey=0444c3bd0e754f77af95cda042d986eb")
    suspend fun getSources(): SourcesResponse
}