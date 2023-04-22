package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.db.dao.SourceDao
import com.example.data.db.entity.SourceEntity

@Database(entities = [SourceEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sourceDao(): SourceDao
}