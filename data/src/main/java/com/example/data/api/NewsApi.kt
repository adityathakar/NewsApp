package com.example.data.api

import com.example.data.model.SourcesResponse
import retrofit2.http.GET

interface NewsApi {

    @GET("top-headlines/sources?language=en&apiKey=0444c3bd0e754f77af95cda042d986eb")
    suspend fun getSources(): SourcesResponse
}