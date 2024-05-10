package com.example.finalprojectandroid

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class WorkoutPlanActivity : AppCompatActivity() {

    private lateinit var selectedDate: String
    private lateinit var textViewDate: TextView
    private lateinit var spinnerWorkoutType: Spinner
    private lateinit var btnSaveWorkout: Button
    private lateinit var buttonChooseDate: Button
    private lateinit var dbHelper: WorkoutDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_plan)

        // Initialize views
        textViewDate = findViewById(R.id.textViewDate)
        spinnerWorkoutType = findViewById(R.id.spinnerWorkoutType)
        btnSaveWorkout = findViewById(R.id.btnSaveWorkout)
        buttonChooseDate = findViewById(R.id.buttonChooseDate)
        //Initializes the database helper
        dbHelper = WorkoutDatabaseHelper(this)

        // Retrieve selected date from intent extras
        selectedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        textViewDate.text = "Choose the date of the workout"

        buttonChooseDate.setOnClickListener {
            showDatePickerDialog()
        }

        // Set click listener for save button
        btnSaveWorkout.setOnClickListener {
            saveWorkout()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            selectedDate = String.format(Locale.getDefault(), "%d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDayOfMonth)
            textViewDate.text = "Selected Date: $selectedDate"
        }, year, month, day).show()
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
            setResult(RESULT_OK)  // Set the result to OK
            finish() // Close activity after saving workout
        }
    }
}
