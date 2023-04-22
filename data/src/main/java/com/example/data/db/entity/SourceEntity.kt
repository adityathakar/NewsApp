package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SourceEntity(
    @PrimaryKey val sourceId: String,
)
