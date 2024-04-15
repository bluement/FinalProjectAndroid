package com.example.finalprojectandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTdeeCalculator = findViewById<Button>(R.id.btnTdeeCalculator)
        val btnBmiCalculator = findViewById<Button>(R.id.btnBmiCalculator)

        btnTdeeCalculator.setOnClickListener {
            startActivity(Intent(this, TdeeCalculatorActivity::class.java))
        }

        btnBmiCalculator.setOnClickListener {
            startActivity(Intent(this, BmiCalculatorActivity::class.java))
        }
    }
}
