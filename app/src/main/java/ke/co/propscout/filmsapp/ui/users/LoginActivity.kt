package ke.co.propscout.filmsapp.ui.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ke.co.propscout.filmsapp.R
import ke.co.propscout.filmsapp.databinding.ActivityLoginBinding
import ke.co.propscout.filmsapp.providers.SessionManager
import ke.co.propscout.filmsapp.ui.HomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel
    private val sessionManager by lazy { SessionManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Init Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        //Init View Model
        val factory = AuthViewModelFactory(this)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)


        //Login Button Click Handler
        binding.loginButton.setOnClickListener {

            val email = binding.emailField.text.toString()
            val password = binding.passwordField.text.toString()

            //Validations
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailField.error = "Invalid Email Address Format"
                binding.emailField.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6) {
                binding.passwordField.error = "At least a char password required"
                binding.passwordField.requestFocus()
                return@setOnClickListener
            }

            viewModel.login(email, password)
        }

        //Sign Up Text View Click Handler
        binding.signUpTextView.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()

        if (::viewModel.isInitialized) {
            viewModel.loginResponse.observe(this, Observer { loginResponse ->
                if (!loginResponse.error) {

                    sessionManager.saveToken(loginResponse.token)

                    Intent(this, HomeActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }.also {
                        startActivity(it)
                    }

                } else {
                    Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }
}