package com.example.domain.model

import java.io.Serializable

data class Article(
    val author: String,
    val title: String,
    val description: String,
    val articleUrl: String,
    val articleImageUrl: String,
) : Serializable
