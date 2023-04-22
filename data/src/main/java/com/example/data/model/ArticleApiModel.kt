package com.example.data.model

import com.google.gson.annotations.SerializedName

data class ArticleApiModel(
    @SerializedName("author")
    val author: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("url")
    val articleUrl: String,
    @SerializedName("urlToImage")
    val articleImageUrl: String,
)
