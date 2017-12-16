package com.choi.pe.desertonhead.Util

import com.choi.pe.desertonhead.Data.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


/**
 * Created by choi on 2017. 12. 16..
 */
interface RetrofitInterface {
    @POST("/auth/login")
    fun Login(@Query("id") id: String, @Query("password") password: String): Call<User>

    @POST("/auth/register")
    fun Register(@Query("id") id: String, @Query("password") password: String,
                 @Query("username") nickname: String, @Query("email") email: String): Call<String>



}
