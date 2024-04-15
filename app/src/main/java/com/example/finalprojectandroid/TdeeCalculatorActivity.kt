package com.example.finalprojectandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class TdeeCalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tdee_calculator)

        val btnCalculateTdee = findViewById<Button>(R.id.btnCalculateTdee)
        val etWeight = findViewById<EditText>(R.id.etWeight)
        val etHeight = findViewById<EditText>(R.id.etHeight)

        btnCalculateTdee.setOnClickListener {
            val weight = etWeight.text.toString().toDoubleOrNull() ?: 0.0
            val height = etHeight.text.toString().toDoubleOrNull() ?: 0.0

            // Calculate TDEE using weight and height
            val tdee = calculateTDEE(weight, height)

            // Start DailyIntakeActivity and pass TDEE value
            val intent = Intent(this, DailyIntakeActivity::class.java)
            intent.putExtra("tdee", tdee)
            startActivity(intent)
        }
    }

    private fun calculateTDEE(weight: Double, height: Double): Double {
        // Implement TDEE calculation logic (e.g., Mifflin-St Jeor equation)
        // This is just a placeholder
        return weight * 24.0 // Placeholder calculation
    }
}
