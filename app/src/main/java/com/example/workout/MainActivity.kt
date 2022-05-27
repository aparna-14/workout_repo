package com.example.workout
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workout.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.flFile?.setOnClickListener{
            val intent = Intent(this,ExerciseActivity::class.java)
            startActivity(intent)
        }
        binding?.flbmi?.setOnClickListener{
            val intent = Intent(this,BmiActivity::class.java)
            startActivity(intent)
        }
        binding?.flhistroy?.setOnClickListener{
            val intent = Intent(this,HistroyActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
