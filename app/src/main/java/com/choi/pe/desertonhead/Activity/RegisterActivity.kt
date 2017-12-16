package com.choi.pe.desertonhead.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.choi.pe.desertonhead.Data.User
import com.choi.pe.desertonhead.R
import com.choi.pe.desertonhead.Util.NetworkHelper
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerBtn.setOnClickListener {
            var id = idText.text.toString()
            var password_ = passwordText.text.toString()
            var rePassword = rePasswordText.text.toString()
            var email = emailText.text.toString()
            var nickname = nicknameText.text.toString()
            if (id != "" && password_ != "" && rePassword != "" && email != "" && nickname != "") {
                if (password_ == rePassword) {
                    var register: Call<String> = NetworkHelper.networkInstance!!.Register(id, password_,nickname,email).apply {
                        enqueue(object : Callback<String> {
                            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                                if (response?.code() == 200) {
                                    toast("회원가입 성공")
                                    finish()
                                }
                                toast(response?.code().toString())
                            }

                            override fun onFailure(call: Call<String>?, t: Throwable?) {
                                Log.e("register", t?.message)
                            }
                        })
                    }
                }
            }else{
                toast("빈칸을 확인해주세요")
            }
        }
    }
}
