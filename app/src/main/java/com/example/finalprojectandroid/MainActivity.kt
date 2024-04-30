import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalprojectandroid.BmiCalculatorActivity
import com.example.finalprojectandroid.DailyIntakeActivity
import com.example.finalprojectandroid.R
import com.example.finalprojectandroid.TdeeCalculatorActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calendarView = findViewById(R.id.calendarView)

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$year-${month + 1}-$dayOfMonth"
            launchActivityForDate(selectedDate)
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
            val todayDate = getCurrentDate()
            launchActivityForDate(todayDate)
        }
    }

    private fun launchActivityForDate(selectedDate: String) {
        val intent = Intent(this, WorkoutPlanActivity::class.java)
        intent.putExtra("selectedDate", selectedDate)
        startActivityForResult(intent, WORKOUT_PLAN_REQUEST_CODE)
    }

    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        return "$year-$month-$dayOfMonth"
    }

    companion object {
        const val WORKOUT_PLAN_REQUEST_CODE = 1
    }
}
