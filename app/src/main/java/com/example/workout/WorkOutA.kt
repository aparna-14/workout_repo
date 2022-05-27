package com.example.workout

import android.app.Application

class WorkOutA: Application() {
    val db by lazy{
        HistoryODatabase.getInstance(this)
    }
}