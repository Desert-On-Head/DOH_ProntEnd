package com.choi.pe.desertonhead.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.choi.pe.desertonhead.Data.User
import com.choi.pe.desertonhead.R
import com.choi.pe.desertonhead.Util.DataManager
import com.choi.pe.desertonhead.Util.NetworkHelper
import kotlinx.android.synthetic.main.activity_forgot.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
        var signInCall: Call<String> = NetworkHelper.networkInstance!!.FindPassword(emailText.text.toString(), nicknameText_.text.toString())
        signInCall.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                Log.e("asd", "" + response?.code())
                when (response?.code()) {
                    200 -> {
                        toast("가입할때 입력했던 이메일을 확인하세요")
                        finish()
                    }
                }
            }

            override fun onFailure(call: Call<String>?, t: Throwable?) {
                Log.e("sign_up_fail", t?.message)
            }
        })
    }
}
