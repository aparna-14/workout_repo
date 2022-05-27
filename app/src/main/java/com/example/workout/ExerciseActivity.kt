package com.example.workout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workout.databinding.ActivityExerciseBinding
import com.example.workout.databinding.BackButtonBinding
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var countDownTimer: CountDownTimer? = null
    private var timerduration: Long = 60000
    private var pauseoffset = 0
    private var countDownTimerExercise: CountDownTimer? = null
    private var pauseoffsetExercise = 0
    private var tts: TextToSpeech? = null
    private var player: MediaPlayer? = null
    private  var exerciseAdapter: ExerciseStatusAdapter ?= null
    private var binding : ActivityExerciseBinding? = null
    private var exerciseList : ArrayList<ExerciseModel>? = null
    private var currentExePosition = -1
    private var  rtDuration: Long = 1
    private var exTimeDuration: Long = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolBarExercise)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        exerciseList = Constants.defaultExerciseList()
        tts = TextToSpeech(this,this)

        binding?.toolBarExercise?.setNavigationOnClickListener{
            dialogButton()
        }

       // binding?.flProgressBar?.visibility = View.INVISIBLE
        setUpRV()
        setUpExercislayiutopositiond()
    }

    override fun onBackPressed() {
        dialogButton()
    }
    private fun dialogButton() {
        val customDialog = Dialog(this)
        val dialogBinding = BackButtonBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.tvrr
        dialogBinding.yesbtn.setOnClickListener{
                    this@ExerciseActivity.finish()
                     customDialog.dismiss()
        }
        dialogBinding.nobtn.setOnClickListener{
               customDialog.dismiss()
        }
        customDialog.show()
    }

    private fun setUpExercislayiutopositiond(){
        binding?.rvExercise?.layoutManager=
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!)
        binding?.rvExercise?.adapter = exerciseAdapter
    }
    private fun setUpRV(){
        try {
            val soundURI = Uri.parse("android.resource://com.example.workout/"+ R.raw.press_start)
            player = MediaPlayer.create(applicationContext,soundURI)
            player?.isLooping = false
            player?.start()
        } catch (e: Exception){
            e.printStackTrace()
        }
        binding?.flProgressBarfl?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvExercise?.visibility = View.INVISIBLE
        binding?.ivImageview?.visibility = View.INVISIBLE
        binding?.upCLevel?.visibility = View.VISIBLE
        binding?.tvUpCExercise?.visibility = View.VISIBLE
        binding?.flProgressBarfl?.visibility = View.INVISIBLE
        if(countDownTimer != null){
            countDownTimer?.cancel()
            pauseoffset=0

        }

        binding?.tvUpCExercise?.text = exerciseList!![currentExePosition + 1].getName()
       setProgressBar()

    }

    private fun setPBExercise(){
        binding?.flProgressBarfl?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvExercise?.visibility = View.VISIBLE
        binding?.ivImageview?.visibility = View.VISIBLE
        binding?.flProgressBarfl?.visibility = View.VISIBLE
        binding?.upCLevel?.visibility = View.INVISIBLE
        binding?.tvUpCExercise?.visibility = View.VISIBLE

        if (countDownTimerExercise != null){
            countDownTimerExercise?.cancel()
            pauseoffsetExercise=0
        }
          speakOut(exerciseList!![currentExePosition].getName())
        binding?.ivImageview?.setImageResource(exerciseList!![currentExePosition].getImage())
        binding?.tvExercise?.text = exerciseList!![currentExePosition].getName()
        setExercisePrBar()
    }
    private fun setProgressBar(){
        binding?.progressBar?.progress = pauseoffset
        countDownTimer = object: CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                pauseoffset++
                binding?.progressBar?.progress = 10 - pauseoffset
                binding?.tvTimer?.text = (10 - pauseoffset).toString()
            }

            override fun onFinish() {
                  currentExePosition++
                exerciseList!![currentExePosition].setisSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                 setPBExercise()
            }

        }.start()
    }
    private fun setExercisePrBar(){
        binding?.progressBarfl?.progress = pauseoffsetExercise
        countDownTimerExercise = object : CountDownTimer(30000,1000){
            override fun onTick(p0: Long) {
                pauseoffsetExercise++
                binding?.progressBarfl?.progress = 30 - pauseoffsetExercise
                binding?.tvTimerfl?.text = (30 - pauseoffsetExercise).toString()
            }

            override fun onFinish() {
               if(currentExePosition < exerciseList?.size!!-1){
                   exerciseList!![currentExePosition].setisSelected(false)
                   exerciseList!![currentExePosition].setisCompleted(true)
                   exerciseAdapter!!.notifyDataSetChanged()

                   setUpRV()
               }else{
                   finish()
                   val intent = Intent(this@ExerciseActivity,FinishActivity::class.java)
                   startActivity(intent)
                 //  Toast.makeText(this@ExerciseActivity,"Congratulations you have completed the 7 minutes exercise",Toast.LENGTH_SHORT).show()
               }
            }

        }.start()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.ENGLISH)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","The Language specified is not supported")
            }
        }else {
            Log.e("TTS","Failed")
        }

    }
    private fun speakOut(text:String){
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }
    override fun onDestroy() {
        super.onDestroy()
        if(countDownTimer != null ){
            countDownTimer?.cancel()
            pauseoffset = 0
        }
        if(countDownTimerExercise != null){
            countDownTimerExercise?.cancel()
            pauseoffsetExercise = 0
        }
        if(player != null){
            player!!.stop()
        }
        if(tts != null){
            tts?.stop()
            tts?.shutdown()
        }
        binding = null
    }


}