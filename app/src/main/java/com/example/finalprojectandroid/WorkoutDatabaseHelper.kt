package com.example.finalprojectandroid

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class WorkoutDatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "workoutDatabase.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "workouts"
        const val COLUMN_ID = "id"
        const val COLUMN_DATE = "date"
        const val COLUMN_WORKOUT_TYPE = "workout_type"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_DATE TEXT,
                $COLUMN_WORKOUT_TYPE TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertWorkout(date: String, workoutType: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_DATE, date)
            put(COLUMN_WORKOUT_TYPE, workoutType)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllWorkoutDates(): List<String> {
        val dates = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT DISTINCT $COLUMN_DATE FROM $TABLE_NAME", null)
        while (cursor.moveToNext()) {
            dates.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)))
        }
        cursor.close()
        return dates
    }
    fun getWorkoutsForDate(date: String): List<String> {
        val workouts = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_WORKOUT_TYPE), // Only fetch the workout type
            "$COLUMN_DATE = ?",           // Filter results where the date column matches
            arrayOf(date),                // The date to match
            null,
            null,
            null
        )
        while (cursor.moveToNext()) {
            workouts.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WORKOUT_TYPE)))
        }
        cursor.close()
        return workouts
    }
    fun deleteWorkout(date: String, workoutType: String): Int {
        val db = writableDatabase
        return db.delete(
            TABLE_NAME,
            "$COLUMN_DATE = ? AND $COLUMN_WORKOUT_TYPE = ?",
            arrayOf(date, workoutType)
        )
    }
}