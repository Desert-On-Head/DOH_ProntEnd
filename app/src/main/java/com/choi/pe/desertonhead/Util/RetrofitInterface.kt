package com.choi.pe.desertonhead.Util

import com.choi.pe.desertonhead.Data.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by choi on 2017. 12. 16..
 */
interface RetrofitInterface {
    @GET("/login")
    fun Login(@Query("password") token: String, @Query("id") id: String): Call<User>
}
