package com.example.workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.workout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {

    private var binding : ActivityFinishBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolBarFinish)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolBarFinish?.setNavigationOnClickListener{
            onBackPressed()
        }
        binding?.ibfinish?.setOnClickListener{
            finish()
        }
        val dao = (application as WorkOutA).db.historyDao()
        addDataToDataBase(dao)
    }
    private fun addDataToDataBase(daoHistory: DaoHistory){
        val c = Calendar.getInstance()
        val  dateObj = c.time
        Log.e("Date: ",""+dateObj)
        val sdf = SimpleDateFormat("dd MM yyyy HH:mm:ss",Locale.getDefault())
        val date = sdf.format(dateObj)
        Log.e("Date: ",""+date)
       lifecycleScope.launch {
           daoHistory.insert(EntityOfHistory(date))
           Log.e("Date: ","Added...")
       }
    }
}