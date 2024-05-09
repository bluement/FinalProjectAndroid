package com.example.finalprojectandroid

import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class WorkoutPlanActivity : AppCompatActivity() {

    private lateinit var selectedDate: String
    private lateinit var textViewDate: TextView
    private lateinit var spinnerWorkoutType: Spinner
    private lateinit var btnSaveWorkout: Button
    private lateinit var dbHelper: WorkoutDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_plan)

        // Initialize views
        textViewDate = findViewById(R.id.textViewDate)
        spinnerWorkoutType = findViewById(R.id.spinnerWorkoutType)
        btnSaveWorkout = findViewById(R.id.btnSaveWorkout)

        //Initializes the database helper
        dbHelper = WorkoutDatabaseHelper(this)

        // Retrieve selected date from intent extras
        selectedDate = intent.getStringExtra("selectedDate") ?: ""
        textViewDate.text = "Selected Date: $selectedDate"

        // Set click listener for save button
        btnSaveWorkout.setOnClickListener {
            saveWorkout()
        }
    }

    private fun saveWorkout() {
        val workoutType = spinnerWorkoutType.selectedItem as String

        if (selectedDate.isEmpty()) {
            Toast.makeText(this, "Invalid date. Please try again.", Toast.LENGTH_SHORT).show()
            return
        }

        // Save workout details to database or perform necessary actions
        // Here you would store workoutType and selectedDate in the database
        // Save workout details to database
        val rowId = dbHelper.insertWorkout(selectedDate, workoutType)
        if (rowId == -1L) {
            Toast.makeText(this, "Error saving workout", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Workout saved successfully", Toast.LENGTH_SHORT).show()
            finish() // Close activity after saving workout
        }
    }
}
