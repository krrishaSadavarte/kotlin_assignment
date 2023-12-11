package com.example.shoopzi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoopzi.Utils.Utils
import com.example.shoopzi.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.siLogin.setOnClickListener {

            startActivity(Intent(this,SingupActivity::class.java))
        }
        binding.btnSignin.setOnClickListener {
//            var email = binding.etEmail.text.toString().trim()
//            var pasword = binding.etPassword.text.toString().trim()
//
//
//            if (!Utils.isValidEmail(email)){
//                binding.etEmail.error="Enter valid Email address"
//            }else if(!Utils.isValidPassword(pasword)){
//                binding.etPassword.error="Enter valid password"
//            }else {
            var intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }}