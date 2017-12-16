package com.choi.pe.desertonhead.Custom

import android.content.Context
import android.content.res.TypedArray
import android.support.constraint.ConstraintLayout
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView

import com.choi.pe.desertonhead.R

/**
 * Created by choi on 2017. 12. 16..
 */

class CustomLogin : ConstraintLayout {
    lateinit internal var img: ImageView
    lateinit internal var loginText: EditText

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
        getAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs) {
        initView()
        getAttrs(attrs, defStyle)
    }

    private fun initView() {
        val infService = Context.LAYOUT_INFLATER_SERVICE
        val li = context.getSystemService(infService) as LayoutInflater
        val v = li.inflate(R.layout.custom_login, this, false)
        addView(v)
        loginText = findViewById<View>(R.id.loginEdt) as EditText
        img = findViewById<View>(R.id.loginImg) as ImageView
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoginEditText)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoginEditText, defStyle, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        val img_resID = typedArray.getResourceId(R.styleable.LoginEditText_img, R.mipmap.ic_id)
        img.setBackgroundResource(img_resID)
        val text_hint = typedArray.getString(R.styleable.LoginEditText_hint)
        loginText.hint = text_hint
        val textColor = typedArray.getColor(R.styleable.LoginEditText_textColor, 0)
        val text_type = typedArray.getInt(R.styleable.LoginEditText_type, 0)
        loginText.inputType = text_type
        typedArray.recycle()
    }

    fun setImg(bg_resID: Int) {
        img.setBackgroundResource(bg_resID)
    }

    fun getLoginText(): String {
        return loginText.text.toString()
    }

}