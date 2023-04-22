package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity(
    @PrimaryKey val url: String,
    val imageUrl: String,
    val author: String,
    val title: String,
    val description: String,
)
