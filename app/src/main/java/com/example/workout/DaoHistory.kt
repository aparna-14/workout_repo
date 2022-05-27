package com.example.workout

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface DaoHistory {
  @Insert
   suspend fun insert(entityOfhistory : EntityOfHistory )
   @Query("SELECT * FROM 'history_table'")
  fun fetAllDates(): Flow<List<EntityOfHistory>>
}