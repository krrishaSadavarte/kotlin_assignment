package com.example.shoopzi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        Handler(Looper.getMainLooper()).postDelayed(
            Runnable {
                startActivity(Intent(this,SingupActivity::class.java))
            },3000
        )
    }
}

