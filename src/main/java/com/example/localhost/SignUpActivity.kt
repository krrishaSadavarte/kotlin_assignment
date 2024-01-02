package com.example.localhost

import ApiClass
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.localhost.databinding.ActivitySignUpBinding
import com.example.localhost.model.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            var name = binding.etName.text.toString().trim()
            var email = binding.etEmail.text.toString().trim()
            var contact = binding.etContact.text.toString().trim()
            createAccount(name,email,contact)
        }
    }

    private fun createAccount(name: String, email: String, contact: String) {
    ApiClass.init().create(2,name,email,contact).enqueue(object : Callback<RegisterResponse> {
        override fun onResponse(
            call: Call<RegisterResponse>,
            response: Response<RegisterResponse>
        ) {
            if(response.isSuccessful){
                var res = response.body()

                if(res!= null){
                    Toast.makeText(applicationContext,"${res.message}",Toast.LENGTH_SHORT).show()
                    if(res.status == "success"){
                        startActivity(Intent(applicationContext,MainActivity::class.java))
                    }
                }
            }
        }

        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
            Log.d("TAG","On failure:")
        }

    })
    }
}