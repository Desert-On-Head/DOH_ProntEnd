package com.choi.pe.desertonhead.Util

import android.content.Context
import android.content.SharedPreferences
import com.choi.pe.desertonhead.Data.User

/**
 * Created by choi on 2017. 12. 16..
 */

class DataManager(val context: Context) {
    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    init {
        preferences = context.getSharedPreferences("DOH", Context.MODE_PRIVATE)
        editor = preferences?.edit()
    }


    fun saveLoginData(bool: Boolean, id: String?, nickname: String?) {
        editor?.putBoolean("login", bool)
        editor?.putString("id", id)
        editor?.putString("nickname", nickname)
        editor?.apply()
    }

    fun getLoginData():Boolean = preferences!!.getBoolean("login",false)

}
