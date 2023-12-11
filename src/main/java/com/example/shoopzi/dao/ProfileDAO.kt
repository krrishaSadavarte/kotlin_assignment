package com.example.shoopzi.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.shoopzi.Models.Profile

@Dao
interface ProfileDAO {
    @Insert
    fun insertProfile(profile: Profile)

    @Query("select * from profile_table")
    fun getAllProfiles():MutableList<Profile>

    @Delete
    fun deletecuisine(cuisine:Profile)

    @Update
    fun updateProfile(cuisine:Profile)
}