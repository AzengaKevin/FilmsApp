package ke.co.propscout.filmsapp

import android.app.Application
import android.util.Log

class FilmsApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Log.d("Application", "onCreate: called")
    }
}