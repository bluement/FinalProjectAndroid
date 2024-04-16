package com.example.finalprojectandroid

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTdeeCalculator = findViewById<Button>(R.id.btnTdeeCalculator)
        val btnBmiCalculator = findViewById<Button>(R.id.btnBmiCalculator)
        val btnDailyIntake = findViewById<Button>(R.id.btnDailyIntake)

        btnTdeeCalculator.setOnClickListener {
            startActivity(Intent(this, TdeeCalculatorActivity::class.java))
        }

        btnBmiCalculator.setOnClickListener {
            startActivity(Intent(this, BmiCalculatorActivity::class.java))
        }
        btnDailyIntake.setOnClickListener{
            startActivity(Intent(this,DailyIntakeActivity::class.java))
        }
    }
}
