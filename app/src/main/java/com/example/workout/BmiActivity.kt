package com.example.workout
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.workout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode
class BmiActivity : AppCompatActivity() {
    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNITS = "US_UNITS"
    }
    private var constView: String = METRIC_UNITS_VIEW
    private var consttUsView : String = US_UNITS
    var binding: ActivityBmiBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //klk
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolBarBmi)
        if(supportActionBar != null ){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolBarBmi?.setNavigationOnClickListener{
            onBackPressed()
            supportActionBar?.title = "CALCULATE BMI"
        }
        binding?.btnForBmi?.setOnClickListener {
            calculatebmi()

        }
       binding?.rgrp?.setOnCheckedChangeListener { _,checkedId: Int ->
           if(checkedId == R.id.rgb){
               visibleUnit()
           } else {
               visibleUSunit()
           }
       }

    }

    private fun visibleUnit(){
        constView = METRIC_UNITS_VIEW
        binding?.tilMUW?.visibility = View.VISIBLE
        binding?.tilMUWhH?.visibility = View.VISIBLE
        binding?.tilMUWhHfeet?.visibility = View.GONE
        binding?.tilMUWhHinch?.visibility = View.GONE
        binding?.tilMUWPound?.visibility = View.GONE
        binding?.etMUW?.text!!.clear()
        binding?.etUMBMIH?.text!!.clear()
        binding?.lldisplay?.visibility = View.INVISIBLE
    }
    private fun visibleUSunit(){
        consttUsView = US_UNITS
        binding?.tilMUW?.visibility = View.INVISIBLE
        binding?.tilMUWhH?.visibility = View.INVISIBLE
        binding?.tilMUWhHfeet?.visibility = View.VISIBLE
        binding?.tilMUWhHinch?.visibility = View.VISIBLE
        binding?.tilMUWPound?.visibility = View.VISIBLE
        binding?.etMUWP?.text!!.clear()
        binding?.etUMBMIHfeet?.text!!.clear()
        binding?.etUMBMIHinch?.text!!.clear()
        binding?.lldisplay?.visibility = View.INVISIBLE


    }
    private fun calculateBmi(bmi: Float){
        val bmiLabel: String
        val bmiDiscription: String
        if (bmi.compareTo(15f) <= 0){
            bmiLabel = "Very severely underWeight"
            bmiDiscription = "Oops! You need to take care of your health"
        }else  if (bmi.compareTo(15f) >= 0 && bmi.compareTo(16f) <= 0 ){
            bmiLabel = " severely underWeight"
            bmiDiscription = "Oops! You need to take care of your health"
        }else if (bmi.compareTo(16f) >= 0 && bmi.compareTo(18.5f) <= 0 ){
            bmiLabel = "  underWeight"
            bmiDiscription = "Oops! You need to take care of your health"
        }else if (bmi.compareTo(18.5f) >= 0 && bmi.compareTo(25f) <= 0 ){
            bmiLabel = " Normal"
            bmiDiscription = "Congratulations Your weight is normal"
        }else if (bmi.compareTo(25f) >= 0 && bmi.compareTo(26f) <= 0){
            bmiLabel = "  Obese class"
            bmiDiscription = "Oops! You need to take care of your health"

        } else {

            bmiLabel = "  obese class "
            bmiDiscription = "OMG! you are in very dangerous condition! "
        }
        val bmiVal = BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()
        binding?.lldisplay?.visibility = View.VISIBLE
        binding?.tvYourValue?.text = bmiVal
        binding?.tvBMIType?.text = bmiLabel

        binding?.tvBMIDescription?.text = bmiDiscription
    }
    private fun validateMU(): Boolean{
        var valid = true
        if(binding?.etMUW?.text.toString().isEmpty()){
            valid = false
        }else if (binding?.etUMBMIH?.text.toString().isEmpty()){
            valid = false
        }
        return valid

    }
    private fun validateUSUnit(): Boolean{
        var isValid = true
        when{
            binding?.etMUWP?.text.toString().isEmpty() ->{
                isValid = false
            }
            binding?.etUMBMIHfeet?.text.toString().isEmpty() ->{
                isValid = false
            }
            binding?.etUMBMIHinch?.text.toString().isEmpty() ->{
                isValid = false
            }
        }
        return isValid

    }
    private fun calculatebmi(){
        if(constView == METRIC_UNITS_VIEW){
            if(validateMU()){
                val heightVal: Float = binding?.etUMBMIH?.text.toString().toFloat()/100
                val weightVal : Float = binding?.etMUW?.text.toString().toFloat()
                val calcculateBmi = weightVal / (heightVal * heightVal)
                calculateBmi(calcculateBmi)
            }
            else{
                Toast.makeText(this,"please enter valid number",Toast.LENGTH_SHORT).show()
            }
        }else {
            if (validateUSUnit()){
                val usUnitHieghtValueFeet: String = binding?.etUMBMIHfeet?.text.toString()
                val usinch : String = binding?.etUMBMIHinch?.text.toString()
                val UsWeight: Float = binding?.etMUWP?.text.toString().toFloat()
                val hightVal = usinch.toFloat() + usUnitHieghtValueFeet.toFloat() * 12
                val bmi = 703 * (UsWeight / (hightVal * hightVal))
                calculateBmi(bmi)

            }else{
                Toast.makeText(this,"please enter valid number",Toast.LENGTH_SHORT).show()
            }
        }
    }

}