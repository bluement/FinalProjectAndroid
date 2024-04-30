package com.example.finalprojectandroid

import WorkoutSchedule
import android.content.Context

class SimpleDatabase(private val context: Context) {

    fun saveWorkoutSchedule(workoutSchedule: WorkoutSchedule) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(workoutSchedule.date, workoutSchedule.workoutType).apply()
    }

    fun getWorkoutSchedule(date: String): WorkoutSchedule? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val workoutType = sharedPreferences.getString(date, null)
        return workoutType?.let { WorkoutSchedule(date, it) }
    }

    companion object {
        private const val PREF_NAME = "WorkoutPreferences"
    }
}