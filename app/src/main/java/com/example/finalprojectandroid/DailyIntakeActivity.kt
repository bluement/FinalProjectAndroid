package com.example.finalprojectandroid

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DailyIntakeActivity : AppCompatActivity() {
    private lateinit var calculator: DailyIntakeCalculator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_intake)

        calculator = DailyIntakeCalculator()

        val calculateButton = findViewById<Button>(R.id.calculateButton)

        calculateButton.setOnClickListener {
            val bmi = findViewById<EditText>(R.id.editTextBMI).text.toString().toDoubleOrNull() ?: 0.0
            val tdee = findViewById<EditText>(R.id.editTextTDEE).text.toString().toDoubleOrNull() ?: 0.0

            calculator.setBMI(bmi)
            calculator.setTDEE(tdee)

            val recommendedIntake = calculator.calculateRecommendedIntake()

            val resultString = "Recommended intake:\n" +
                    "Protein: ${recommendedIntake["Protein"]} grams\n" +
                    "Fat: ${recommendedIntake["Fat"]} grams\n" +
                    "Carbohydrates: ${recommendedIntake["Carbohydrates"]} grams"

            findViewById<TextView>(R.id.resultTextView).text = resultString
        }
    }
}