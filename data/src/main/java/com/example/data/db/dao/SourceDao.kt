package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.entity.SourceEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SourceDao {

    @Query("Select * FROM SourceEntity")
    abstract fun getSources(): Flow<List<SourceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addSource(source: SourceEntity)

    @Delete
    abstract suspend fun removeSource(source: SourceEntity)

    @Query("SELECT EXISTS (SELECT 1 FROM SourceEntity WHERE sourceId = :sourceId)")
    abstract suspend fun sourceExist(sourceId: String): Boolean
}