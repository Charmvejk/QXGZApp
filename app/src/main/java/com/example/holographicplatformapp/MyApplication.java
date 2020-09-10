package com.example.holographicplatformapp;

import android.app.Application;
import android.graphics.Typeface;

public class MyApplication extends Application {
    private Typeface typeface;
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = (MyApplication) getApplicationContext();
        typeface = Typeface.createFromAsset(instance.getAssets(), "fonts/wryh.ttf");//下载的字体
    }

    public static  MyApplication getInstace() {
        return instance;
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }
}
