package com.example.shoopzi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoopzi.Models.Profile
import com.example.shoopzi.adapter.CuisineListAdapter
import com.example.shoopzi.database.AppDatabase
import com.example.shoopzi.databinding.ActivityCuisineListBinding

class CuisineListActivity : AppCompatActivity() {
    lateinit var binding:ActivityCuisineListBinding
    lateinit var madapter :CuisineListAdapter
    private var  Lprofile : MutableList<Profile> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCuisineListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        madapter = CuisineListAdapter(this, Lprofile)

        binding.recyclerProfile.layoutManager = LinearLayoutManager(this)
        binding.recyclerProfile.adapter = madapter
        madapter.setItems(Lprofile)


        madapter.itemEditClicklistener={position, cuisine ->
            var intent=Intent(this,CuisineActivity::class.java)
            intent.putExtra("cuisine",cuisine)
            startActivity(intent)
            
        }
        madapter.itemDelClicklistener={position, cuisine ->
        try {
            AppDatabase.getInstance(this)?.ProfileDao()!!.deletecuisine(cuisine)
            Toast.makeText(this,"deleted",Toast.LENGTH_SHORT).show()
            madapter.deleteItem(position)
        }catch (e:Exception){
            e.printStackTrace()
        }
        }
        binding.btnAddCuisines.setOnClickListener {
            var intent=Intent(this,CuisineActivity::class.java)
            startActivity(intent)
        }
    }



    private fun readCuisineList() {

      Lprofile=  AppDatabase.getInstance(this)?.ProfileDao()!!.getAllProfiles()
      madapter.setItems(Lprofile)
    }

    override fun onResume() {
        super.onResume()
        readCuisineList()

    }
}