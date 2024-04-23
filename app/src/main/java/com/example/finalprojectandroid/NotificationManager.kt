package com.example.finalprojectandroid

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission


import android.os.Build
import android.os.PowerManager
import android.os.WorkSource
import androidx.core.content.getSystemService

class NotificationManager(private val context: Context) {

    fun scheduleNotification(timeInMillis: Long, workoutName: String) {
        val alarmManager = context.getSystemService<AlarmManager>()
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra("workoutName", workoutName)
        }
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (alarmManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                // Check if the app can schedule exact alarms
                val canScheduleExactAlarms = alarmManager.canScheduleExactAlarms()
                if (!canScheduleExactAlarms) {
                    // Handle the case where exact alarms cannot be scheduled
                    // You might want to fall back to non-exact alarms or adjust your scheduling logic
                    // For demonstration, we can log a warning here
                    println("Cannot schedule exact alarms on this device")
                }
            }

            // Set the alarm
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
        } else {
            // Handle case where AlarmManager service is not available
            // This scenario is unlikely as getSystemService should not return null for AlarmManager
            println("AlarmManager service is not available")
        }
    }
}

