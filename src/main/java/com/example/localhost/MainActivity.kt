package com.example.localhost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.localhost.databinding.ActivityMainBinding
import com.example.localhost.databinding.ItemDailogLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDailog.setOnClickListener {
            var message = "msg"
            showBottomSheetDialog(message)
        }
    }

    private fun showBottomSheetDialog( msg :String) {
        var bind = ItemDailogLayoutBinding.inflate(layoutInflater)
        bind.etName.setText(msg)
        var dailog = BottomSheetDialog(this)
        dailog.setContentView(bind.root)
        dailog.show()

        bind.btnSubmit.setOnClickListener {
            dailog.dismiss()
        }
    }
}