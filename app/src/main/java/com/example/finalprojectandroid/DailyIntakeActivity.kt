package com.example.finalprojectandroid

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DailyIntakeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_intake)

        val tvTdeeResult = findViewById<TextView>(R.id.tvTdeeResult)
        val tvBmiResult = findViewById<TextView>(R.id.tvBmiResult)
        val tvDailyIntake = findViewById<TextView>(R.id.tvDailyIntake)

        val tdee = intent.getDoubleExtra("tdee", 0.0)
        val bmi = intent.getDoubleExtra("bmi", 0.0)

        tvTdeeResult.text = "Your TDEE: $tdee"
        tvBmiResult.text = "Your BMI: $bmi"

        // Calculate daily intake recommendation based on TDEE and BMI
        val dailyIntake = calculateDailyIntake(tdee, bmi)
        tvDailyIntake.text = "Recommended Daily Intake: $dailyIntake kcal"
    }

    private fun calculateDailyIntake(tdee: Double, bmi: Double): Int {
        // Implement daily intake recommendation logic based on TDEE and BMI
        // This is just a placeholder
        return (tdee * 0.85).toInt() // Placeholder calculation (e.g., 85% of TDEE for weight loss)
    }
}
