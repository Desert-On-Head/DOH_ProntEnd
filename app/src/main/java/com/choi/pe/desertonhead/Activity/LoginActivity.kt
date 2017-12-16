package com.choi.pe.desertonhead.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.choi.pe.desertonhead.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginBtn.setOnClickListener{
            startActivity<MainActivity>()
            toast(loginId.loginText + password.loginText)
        }

        register.setOnClickListener {
            startActivity<RegisterActivity>()
        }

        findPassword.setOnClickListener{
            startActivity<ForgotActivity>()
        }
    }
}
