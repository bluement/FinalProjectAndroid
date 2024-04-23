package com.example.finalprojectandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import android.widget.CalendarView


class MainActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calendarView = findViewById(R.id.calendarView)
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val selectedDate = "$year-${month + 1}-$dayOfMonth"
            launchActivityForDate(selectedDate)
        }

        findViewById<Button>(R.id.btnBmiCalculator).setOnClickListener {
            startActivity(Intent(this, BmiCalculatorActivity::class.java))
        }

        findViewById<Button>(R.id.btnTdeeCalculator).setOnClickListener {
            startActivity(Intent(this, TdeeCalculatorActivity::class.java))
        }

        findViewById<Button>(R.id.btnDailyIntake).setOnClickListener {
            startActivity(Intent(this, DailyIntakeActivity::class.java))
        }

        findViewById<Button>(R.id.btnWorkoutPlan).setOnClickListener {
            startActivity(Intent(this, WorkoutPlanActivity::class.java))
        }
    }

    private fun launchActivityForDate(selectedDate: String) {
        val intent = Intent(this, WorkoutPlanActivity::class.java)
        intent.putExtra("selectedDate", selectedDate)
        startActivity(intent)
    }
}

