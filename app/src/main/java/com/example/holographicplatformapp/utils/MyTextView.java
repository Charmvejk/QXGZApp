package com.example.holographicplatformapp.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.holographicplatformapp.MyApplication;

public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
        //设置字体
        setTypeface(MyApplication.getInstace().getTypeface());
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //设置字体
        setTypeface(MyApplication.getInstace().getTypeface());
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置字体
        setTypeface(MyApplication.getInstace().getTypeface());
    }
}
