package com.choi.pe.desertonhead.Util

import android.content.Context
import android.net.ConnectivityManager
import android.annotation.SuppressLint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by choi on 2017. 12. 16..
 */
class NetworkHelper() {
    lateinit var context: Context
    val url = "http://soylatte.kr"
    val port = 3000

    constructor(context: Context) : this() {
        this.context = context
    }

    var retrofit: Retrofit? = null

    fun getNetworkInstance(): RetrofitInterface {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(url + ":" + port)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit!!.create(RetrofitInterface::class.java)
    }

    @SuppressLint("MissingPermission")
    fun returnNetworkState(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }
}
