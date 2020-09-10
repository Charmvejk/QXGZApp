package com.example.holographicplatformapp.activity.linecharts;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

import static com.example.holographicplatformapp.utils.NumberUtils.amountConversion;
/*
 *
 * 设置Y轴右样式
 * */

public class MyValueFormatter implements YAxisValueFormatter, ValueFormatter {

    private DecimalFormat mFormat;

    public MyValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##0.0");
    }



    @Override
    public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
        return amountConversion(v, "double");
    }

    @Override
    public String getFormattedValue(float v, YAxis yAxis) {
        return amountConversion(v, "double");
    }
}
