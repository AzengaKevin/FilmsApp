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
import ke.co.propscout.filmsapp.databinding.ActivityRegisterBinding
import ke.co.propscout.filmsapp.ui.HomeActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        //Init View Model
        val factory = AuthViewModelFactory(this)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

        //Register Button CLick Handler
        binding.registerButton.setOnClickListener {

            val name = binding.nameField.text.toString()
            val email = binding.emailField.text.toString()
            val password = binding.passwordField.text.toString()

            //Validations
            if (name.isEmpty()) {
                binding.nameField.error = "Name is required"
                binding.nameField.requestFocus()
                return@setOnClickListener
            }

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

    override fun onStart() {
        super.onStart()

        if (::viewModel.isInitialized) {
            viewModel.registerResponse.observe(this, Observer { registerResponse ->
                if (!registerResponse.error) {
                    Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}