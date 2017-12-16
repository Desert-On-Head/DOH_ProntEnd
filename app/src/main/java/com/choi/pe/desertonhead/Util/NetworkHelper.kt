package com.choi.pe.desertonhead.Util

import android.content.Context
import android.net.ConnectivityManager
import android.annotation.SuppressLint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import com.google.gson.Gson




/**
 * Created by choi on 2017. 12. 16..
 */
class NetworkHelper(private val context: Context) {
    companion object {
        val url = "http:/soylatte.kr"
        val port = 3000

        var retrofit: Retrofit? = null

        val networkInstance: RetrofitInterface?
            get() {
                val gson = GsonBuilder()
                        .setLenient()
                        .create()

                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                            .baseUrl(url + ":" + port)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build()
                }
                return retrofit?.create(RetrofitInterface::class.java)
            }

        @SuppressLint("MissingPermission")
        fun returnNetworkState(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
        }
    }
}