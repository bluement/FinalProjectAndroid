package com.example.finalprojectandroid

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TdeeCalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tdee_calculator)

        val btnCalculateTdee = findViewById<Button>(R.id.btnCalculateTdee)
        val etWeight = findViewById<EditText>(R.id.etWeight)
        val etHeight = findViewById<EditText>(R.id.etHeight)
        val etAge = findViewById<EditText>(R.id.etAge)
        val spnGender = findViewById<Spinner>(R.id.spnGender)
        val spnActivity = findViewById<Spinner>(R.id.spnActivity)
        val tvTdeeResult = findViewById<TextView>(R.id.tvTdeeResult)  // TextView to display the TDEE result

        btnCalculateTdee.setOnClickListener {
            val weight = etWeight.text.toString().toDoubleOrNull() ?: 0.0
            val height = etHeight.text.toString().toDoubleOrNull() ?: 0.0
            val age = etAge.text.toString().toIntOrNull() ?: 0
            val gender = spnGender.selectedItem.toString()
            val activityLevel = spnActivity.selectedItem.toString()

            if (weight > 0 && height > 0 && age > 0) {
                val bmr = calculateBMR(gender, weight, height, age)
                val tdee = calculateTDEE(bmr, activityLevel)
                tvTdeeResult.text = String.format("Your TDEE is: %.2f kcal", tdee)
            } else {
                tvTdeeResult.text = "Please enter valid values for weight, height, and age."
            }
        }
    }

    private fun calculateBMR(gender: String, weight: Double, height: Double, age: Int): Double {
        return if (gender == "Male") {
            10 * weight + 6.25 * height - 5 * age + 5
        } else {
            10 * weight + 6.25 * height - 5 * age - 161
        }
    }

    private fun calculateTDEE(bmr: Double, activityLevel: String): Double {
        val multiplier = when (activityLevel) {
            "Sedentary" -> 1.2
            "Light Exercise" -> 1.375
            "Moderate Exercise" -> 1.55
            "Heavy Exercise" -> 1.725
            "Athlete" -> 1.9
            else -> 1.0
        }
        return bmr * multiplier
    }
}
