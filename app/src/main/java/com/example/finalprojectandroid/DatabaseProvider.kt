import android.content.Context
import com.example.finalprojectandroid.SimpleDatabase

object DatabaseProvider {

    private var database: SimpleDatabase? = null

    fun getDatabase(context: Context): SimpleDatabase {
        return database ?: synchronized(this) {
            val instance = SimpleDatabase(context.applicationContext)
            database = instance
            instance
        }
    }
}
