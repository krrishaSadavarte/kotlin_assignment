package com.example.localhost.model

import com.google.gson.annotations.SerializedName

data class User(
    var  id:Int,
    var name:String,
    var email:String,
    @SerializedName("mobile")
    var contact:String
)
