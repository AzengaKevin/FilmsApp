package ke.co.propscout.filmsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import ke.co.propscout.filmsapp.R
import ke.co.propscout.filmsapp.providers.SessionManager
import ke.co.propscout.filmsapp.ui.users.LoginActivity

class SplashActivity : AppCompatActivity() {

    private val sessionManager by lazy {
        SessionManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onStart() {
        super.onStart()

        Handler().postDelayed({
            /*
             * Check whether the user in authenticated and redirect appropriately
             *  a) LoginActivity::class.java
             *  b) HomeActivity::class.java
             */
            if (sessionManager.getToken() != null) {
                Intent(this, HomeActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }.also {
                    startActivity(it)
                }
            } else {

                Intent(this, LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }.also {
                    startActivity(it)
                }
            }

        }, 3000)
    }
}