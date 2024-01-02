package com.example.localhost


import com.example.localhost.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("student.php")
    fun create(
        @Field("flag") flag:Int,
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("mobile") contact:String
    ): Call<RegisterResponse>


}
