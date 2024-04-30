package com.example.finalprojectandroid

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BmiCalculator : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_calculator)

        val btnCalculateBmi = findViewById<Button>(R.id.btnCalculateBmi)
        val etWeight = findViewById<EditText>(R.id.etWeight)
        val etHeight = findViewById<EditText>(R.id.etHeight)
        val tvBmiResult = findViewById<TextView>(R.id.tvBmiResult)  // TextView for BMI result

        btnCalculateBmi.setOnClickListener {
            val weight = etWeight.text.toString().toDoubleOrNull() ?: 0.0
            val height = etHeight.text.toString().toDoubleOrNull() ?: 0.0

            if (weight > 0 && height > 0) {
                val bmi = calculateBMI(weight, height)
                tvBmiResult.text = String.format("Your BMI is: %.2f", bmi)
            } else {
                tvBmiResult.text = "Please enter valid values for weight and height."
            }
        }
    }

    private fun calculateBMI(weight: Double, height: Double): Double {
        val heightMeters = height / 100
        return weight / (heightMeters * heightMeters)
    }
}
