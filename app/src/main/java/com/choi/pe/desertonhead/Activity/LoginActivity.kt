package com.choi.pe.desertonhead.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.choi.pe.desertonhead.Data.User
import com.choi.pe.desertonhead.R
import com.choi.pe.desertonhead.Util.DataManager
import com.choi.pe.desertonhead.Util.NetworkHelper
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val dataManager: DataManager = DataManager(applicationContext)
        if (dataManager.getLoginData()) {
            startActivity<MainActivity>()
            finish()
        }
        loginBtn.setOnClickListener {
            var signInCall: Call<User> = NetworkHelper.networkInstance!!.Login(loginId.getLoginText(), password.getLoginText())
            signInCall.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>?, response: Response<User>?) {
                    Log.e("asd", "" + response?.code())
                    when (response?.code()) {
                        200 -> {
                            response.body().let {
                                dataManager.saveLoginData(true, it?.id, it?.username)
                                startActivity<MainActivity>()
                                finish()
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<User>?, t: Throwable?) {
                    Log.e("sign_up_fail", t?.message)
                }
            })
        }

        register.setOnClickListener {
            startActivity<RegisterActivity>()
        }

        findPassword.setOnClickListener {
            startActivity<ForgotActivity>()
        }
    }
}
