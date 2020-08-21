package ke.co.propscout.filmsapp.ui.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import ke.co.propscout.filmsapp.R
import ke.co.propscout.filmsapp.databinding.ActivityRegisterBinding
import ke.co.propscout.filmsapp.ui.HomeActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        //Register Button CLick Handler
        binding.registerButton.setOnClickListener {

            Intent(this, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }.also {
                startActivity(it)
            }

        }

        //Sign In Text View CLick Handler
        binding.signInTextView.setOnClickListener {
            finish()
        }
    }
}