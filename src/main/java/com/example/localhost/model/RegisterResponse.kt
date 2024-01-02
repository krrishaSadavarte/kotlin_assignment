package com.example.localhost.model

data class RegisterResponse(
    var status:String,
    var message:String,
    var user: User
)
