package com.example.finalprojectandroid

import WorkoutSchedule


interface WorkoutScheduleDAO {
    fun saveWorkoutSchedule(workoutSchedule: WorkoutSchedule)
    fun getWorkoutSchedule(date: String): WorkoutSchedule?
}
