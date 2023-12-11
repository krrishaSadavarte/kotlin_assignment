package com.example.shoopzi

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.shoopzi.prefrence.PrefManager
import com.example.shoopzi.Utils.Utils
import com.example.shoopzi.databinding.ActivitySingupBinding

class SingupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySingupBinding

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.siLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))

            var name = binding.etName.text.toString().trim()
            var email = binding.etEmail.text.toString().trim()
            var password = binding.etPassword.text.toString().trim()
            var confirm_password = binding.etRepassword.text.toString().trim()

            Toast.makeText(this,"$email $name $password $confirm_password", Toast.LENGTH_SHORT).show()


            resetFocus()

            if (name.isEmpty()) {
                // show error message on etName
                // binding.etName.error = "Enter valid name"
                showError(binding.etName, "Enter valid name")
            } else if (!Utils.isValidEmail(email)) {
                // show error message on etEmail
                // binding.etEmail.error = "Enter valid email"
                showError(binding.etEmail, "Enter valid email")
            } else if (!Utils.isValidPassword(password)) {
                // binding.etPassword.error = "Enter valid password"
                showError(binding.etPassword, "Enter valid password")
            } else if (confirm_password != password) {
                showError(binding.etRepassword, "Password mismatch")
                binding.etRepassword.setText("")
            } else {
                var manager = PrefManager(this)
                manager.updateLoginStatus(true)
                var intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()

            }

        }

    }

    private fun resetFocus() {
        binding.etName.setBackgroundResource(R.drawable.edittext_white_bg)
        binding.etEmail.setBackgroundResource(R.drawable.edittext_white_bg)
        binding.etPassword.setBackgroundResource(R.drawable.edittext_white_bg)
        binding.etRepassword.setBackgroundResource(R.drawable.edittext_white_bg)
    }

    private fun showError(editText: EditText, error: String) {
        editText.setBackgroundResource(R.drawable.edittext_red_bg)
        editText.requestFocus()
        Toast.makeText(this, "$error", Toast.LENGTH_SHORT).show()
    }

}



