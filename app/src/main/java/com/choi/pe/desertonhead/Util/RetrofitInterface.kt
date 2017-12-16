package com.choi.pe.desertonhead.Util

import com.choi.pe.desertonhead.Data.User
import retrofit2.Call
import retrofit2.http.*


interface RetrofitInterface {
    @POST("/auth/login")
    @FormUrlEncoded
    fun Login(@Field("id") id: String, @Field("password") password: String): Call<User>

    @POST("/auth/register")
    @FormUrlEncoded
    fun Register(@Field("id") id: String, @Field("password") password: String,
                 @Field("username") nickname: String, @Field("email") email: String): Call<String>

    @POST("/auth/findpassword")
    @FormUrlEncoded
    fun FindPassword(@Field("username") nickname: String, @Field("id") id: String): Call<String>
}




