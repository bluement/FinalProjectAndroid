package com.example.finalprojectandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class BmiCalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_calculator)

        val btnCalculateBmi = findViewById<Button>(R.id.btnCalculateBmi)
        val etWeight = findViewById<EditText>(R.id.etWeight)
        val etHeight = findViewById<EditText>(R.id.etHeight)

        btnCalculateBmi.setOnClickListener {
            val weight = etWeight.text.toString().toDoubleOrNull() ?: 0.0
            val height = etHeight.text.toString().toDoubleOrNull() ?: 0.0

            // Calculate BMI using weight and height
            val bmi = calculateBMI(weight, height)

            // Start DailyIntakeActivity and pass BMI value
            val intent = Intent(this, DailyIntakeActivity::class.java)
            intent.putExtra("bmi", bmi)
            startActivity(intent)
        }
    }

    private fun calculateBMI(weight: Double, height: Double): Double {
        // Implement BMI calculation logic
        val heightMeters = height / 100
        return weight / (heightMeters * heightMeters)
    }
}
