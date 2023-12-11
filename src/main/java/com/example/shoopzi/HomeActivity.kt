package com.example.shoopzi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.shoopzi.databinding.ActivityHomeBinding
import com.example.shoopzi.databinding.ActivityLoginBinding
import com.example.shoopzi.fragments.NotificationFragment
import com.example.shoopzi.fragments.OrderFragment
import com.example.shoopzi.fragments.favouritefragment
import com.example.shoopzi.fragments.homeFragment
import com.example.shoopzi.prefrence.PrefManager

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var manager:PrefManager
    private lateinit var appBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        supportActionBar?.title=""
        manager = PrefManager(this)
        var user = manager.getUserCredential()

        if (user != null){
            Toast.makeText(this,"welcome,${user.name}",Toast.LENGTH_SHORT).show()
        }

        binding.toolBar.setNavigationOnClickListener {
            binding.root.open()
        }

        addFragment(homeFragment())

        binding.navigationView.setNavigationItemSelectedListener { menuItem->
            menuItem.isChecked=true
            binding.root.close()
            when(menuItem.itemId){
                R.id.nav_home->{
                    addFragment(homeFragment())
                    true
                }
                R.id.nav_favourite->{
                    addFragment(favouritefragment())

                    true
                }
                R.id.nav_notification->{
                    addFragment(NotificationFragment())
                    true
                }
                R.id.nav_order->{
                    addFragment(OrderFragment())
                    true
                }
                R.id.nav_logout->{
                    manager.updateLoginStatus(false)
                    var intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                    finishAffinity()
                    true
                }
                R.id.nav_cuisines->{
                    var intent=Intent(this,CuisineListActivity::class.java)
                    startActivity(intent)
                    true
                }
                else-> false

            }

        }

//        appBarDrawerToggle =ActionBarDrawerToggle(this,binding.root,binding.toolBar)
//        binding.logout.setOnClickListener {
//
//            var intent = Intent(this,LoginActivity::class.java)
//            startActivity(intent)
//            finishAffinity()
//        }

    }

    private fun addFragment(fragment: Fragment) {
        var manager=supportFragmentManager
        var transaction  = manager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }
}