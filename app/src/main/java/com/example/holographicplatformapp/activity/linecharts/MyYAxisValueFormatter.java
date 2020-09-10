package com.example.holographicplatformapp.activity.linecharts;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;

import java.text.DecimalFormat;

import static com.example.holographicplatformapp.utils.NumberUtils.amountConversion;
/*
*
* 设置Y轴样式
* */

public class MyYAxisValueFormatter implements YAxisValueFormatter {

    private DecimalFormat mFormat;

    public MyYAxisValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##0.0");
    }

    @Override
    public String getFormattedValue(float value, YAxis yAxis) {
        return ""+amountConversion(value,"int");


    }
}
