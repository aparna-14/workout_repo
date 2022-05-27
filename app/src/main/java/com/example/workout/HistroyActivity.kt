package com.example.workout
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workout.databinding.ActivityHistroyBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
class HistroyActivity : AppCompatActivity() {
    private var binding: ActivityHistroyBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistroyBinding.inflate(layoutInflater)
        setContentView(binding?.root)
       setSupportActionBar(binding?.toolBarHistroy)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "HISTORY"
        }
        binding?.toolBarHistroy?.setNavigationOnClickListener{
            onBackPressed()
        }
        val dao = (application as WorkOutA).db.historyDao()
        getAllCompletedDates(dao)
    }
    private fun getAllCompletedDates(historyDao: DaoHistory){
        lifecycleScope.launch{
            historyDao.fetAllDates().collect{allCompetedDateList ->
               if(allCompetedDateList.isNotEmpty()){
                   binding?.tvHistory?.visibility = View.VISIBLE
                   binding?.rvTEXT?.visibility = View.VISIBLE
                   binding?.tvNodata?.visibility = View.INVISIBLE
                   binding?.rvTEXT?.layoutManager = LinearLayoutManager(this@HistroyActivity)
                   val dates = ArrayList<String>()
                   for (date in allCompetedDateList){
                       dates.add(date.date)
                   }
                   val historyAdapter = HistoryAdapter(dates)
                   binding?.rvTEXT?.adapter = historyAdapter
               }else{
                   binding?.tvHistory?.visibility = View.GONE
                   binding?.rvTEXT?.visibility = View.GONE
                   binding?.tvNodata?.visibility = View.VISIBLE

               }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}