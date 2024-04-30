import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalprojectandroid.R

class WorkoutPlanActivity : AppCompatActivity() {

    private lateinit var selectedDate: String
    private lateinit var textViewDate: TextView
    private lateinit var spinnerWorkoutType: Spinner
    private lateinit var btnSaveWorkout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_plan)

        textViewDate = findViewById(R.id.textViewDate)
        spinnerWorkoutType = findViewById(R.id.spinnerWorkoutType)
        btnSaveWorkout = findViewById(R.id.btnSaveWorkout)

        selectedDate = intent.getStringExtra("selectedDate") ?: ""
        textViewDate.text = "Selected Date: $selectedDate"

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

        // Here you can save workoutType and selectedDate using SharedPreferences or other local storage
        // For example:
        val sharedPreferences = getSharedPreferences("WorkoutPreferences", MODE_PRIVATE)
        sharedPreferences.edit().putString(selectedDate, workoutType).apply()

        Toast.makeText(this, "Workout saved successfully", Toast.LENGTH_SHORT).show()
        setResult(Activity.RESULT_OK)
        finish()
    }
}
