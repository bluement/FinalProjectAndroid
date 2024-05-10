package com.example.finalprojectandroid

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

import android.widget.CalendarView
import android.widget.Toast
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var dbHelper: WorkoutDatabaseHelper
    private val workoutDates = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = WorkoutDatabaseHelper(this)
        calendarView = findViewById(R.id.calendarView)

        loadWorkoutDates()

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, dayOfMonth)
            showWorkoutDetails(selectedDate)
        }

        findViewById<Button>(R.id.btnBmiCalculator).setOnClickListener {
            startActivity(Intent(this, BmiCalculator::class.java))
        }

        findViewById<Button>(R.id.btnTdeeCalculator).setOnClickListener {
            startActivity(Intent(this, TdeeCalculatorActivity::class.java))
        }

        findViewById<Button>(R.id.btnDailyIntake).setOnClickListener {
            startActivity(Intent(this, DailyIntakeActivity::class.java))
        }

        findViewById<Button>(R.id.btnWorkoutPlan).setOnClickListener {
            startActivity(Intent(this, WorkoutPlanActivity::class.java))
            startActivityForResult(intent, REQUEST_CODE_ADD_WORKOUT)
        }
    }
    private fun showWorkoutDetails(date: String) {
        val workouts = dbHelper.getWorkoutsForDate(date)

        if (workouts.isNotEmpty()) {
            AlertDialog.Builder(this)
                .setTitle("Workouts for $date")
                .setItems(workouts.toTypedArray()) { dialog, which ->
                    // The index corresponds to the clicked workout type
                    val workoutToDelete = workouts[which]
                    confirmAndDeleteWorkout(date, workoutToDelete)
                }
                .setPositiveButton("Cancel", null)
                .show()
        } else {
            Toast.makeText(this, "No workout planned for this day.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun confirmAndDeleteWorkout(date: String, workoutType: String) {
        AlertDialog.Builder(this)
            .setTitle("Delete Workout")
            .setMessage("Are you sure you want to delete the workout '$workoutType' on $date?")
            .setPositiveButton("Yes") { _, _ ->
                val rowsDeleted = dbHelper.deleteWorkout(date, workoutType)
                if (rowsDeleted > 0) {
                    Toast.makeText(this, "Workout deleted successfully.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error deleting workout.", Toast.LENGTH_SHORT).show()
                }
                // Refresh workout data after deletion
                loadWorkoutDates()
            }
            .setNegativeButton("No", null)
            .show()
    }
    override fun onResume() {
        super.onResume()
        loadWorkoutDates()
    }
    private fun loadWorkoutDates() {
        val dates = dbHelper.getAllWorkoutDates()
        workoutDates.clear()
        workoutDates.addAll(dates)
        Log.d("MainActivity", "Loaded workout dates: $workoutDates")
    }

    private fun launchActivityForDate(selectedDate: String) {
        val intent = Intent(this, WorkoutPlanActivity::class.java)
        intent.putExtra("selectedDate", selectedDate)
        startActivityForResult(intent, REQUEST_CODE_ADD_WORKOUT)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_WORKOUT && resultCode == RESULT_OK) {
            loadWorkoutDates()  // Refresh workout dates
        }
    }
    companion object {
        private const val REQUEST_CODE_ADD_WORKOUT = 1
    }
}

