package com.example.workout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class EntityOfHistory(
    @PrimaryKey
    val date: String
)
