package com.example.shoopzi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoopzi.Models.Profile
import com.example.shoopzi.dao.ProfileDAO

@Database(entities = [Profile::class], version = 1)
 abstract class AppDatabase :RoomDatabase(){
     abstract fun ProfileDao():ProfileDAO


     companion object{
         private var INSTANCE:AppDatabase?=null

         fun getInstance(context: Context):AppDatabase?{
             if (INSTANCE == null){
                 synchronized(this ){
                     INSTANCE= Room.databaseBuilder( context,AppDatabase::class.java,"shopzi.db",).
                     allowMainThreadQueries().build()

                 }
             }
             return INSTANCE
         }
     }
}