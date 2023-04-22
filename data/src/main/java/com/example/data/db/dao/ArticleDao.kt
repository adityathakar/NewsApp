package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ArticleDao {

    @Query("Select * FROM ArticleEntity")
    abstract fun getArticles(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addArticle(article: ArticleEntity)

    @Delete
    abstract suspend fun removeArticle(article: ArticleEntity)

    @Query("SELECT EXISTS (SELECT 1 FROM ArticleEntity WHERE url = :url)")
    abstract suspend fun articleExist(url: String): Boolean
}