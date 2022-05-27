package com.example.workout

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [EntityOfHistory::class], version = 1)
abstract class HistoryODatabase : RoomDatabase(){
    abstract fun historyDao():DaoHistory
    companion object {
        @Volatile
        private var INSTANCE: HistoryODatabase? = null
        fun getInstance(context: Context): HistoryODatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryODatabase::class.java,
                        "history_database").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}