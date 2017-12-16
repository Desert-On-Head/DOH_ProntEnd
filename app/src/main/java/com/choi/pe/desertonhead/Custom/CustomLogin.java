package com.choi.pe.desertonhead.Custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.choi.pe.desertonhead.R;

/**
 * Created by choi on 2017. 12. 16..
 */

public class CustomLogin extends ConstraintLayout {
    ImageView img;
    EditText loginText;

    public CustomLogin(Context context) {
        super(context);
        initView();
    }

    public CustomLogin(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        getAttrs(attrs);
    }

    public CustomLogin(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        initView();
        getAttrs(attrs, defStyle);
    }

    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.custom_login, this, false);
        addView(v);
        loginText = (EditText) findViewById(R.id.loginEdt);
        img = (ImageView) findViewById(R.id.loginImg);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LoginEditText);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LoginEditText, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray) {
        int img_resID = typedArray.getResourceId(R.styleable.LoginEditText_img, R.mipmap.ic_id);
        img.setBackgroundResource(img_resID);
        String text_hint = typedArray.getString(R.styleable.LoginEditText_hint);
        loginText.setHint(text_hint);
        int textColor = typedArray.getColor(R.styleable.LoginEditText_textColor, 0);
        int text_type = typedArray.getInt(R.styleable.LoginEditText_type, 0);
        loginText.setInputType(text_type);
        typedArray.recycle();
    }

    public void setImg(int bg_resID) {
        img.setBackgroundResource(bg_resID);
    }

    public String getLoginText() {
        return loginText.getText().toString();
    }

}