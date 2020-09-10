package com.example.holographicplatformapp.fragment;

import android.content.Intent;

import android.view.View;
import android.widget.Button;

import com.example.holographicplatformapp.R;
import com.example.holographicplatformapp.activity.BaseFragment;
import com.example.holographicplatformapp.activity.tj.StatisticsActivity;

/*
 * 服务页
 *
 * */
public class ServiceFragment extends BaseFragment implements View.OnClickListener {
    private Button btnStatistics;
    private Button btnService;

    public static ServiceFragment newInstance() {
        return new ServiceFragment();
    }


    @Override
    protected void init(View mContentView) {
        btnStatistics = mContentView.findViewById(R.id.btn_tj);
        btnService = mContentView.findViewById(R.id.btn_fw);
        btnStatistics.setOnClickListener(this);
        btnService.setOnClickListener(this);

    }

    @Override
    protected void setUpView() {
        initToolbar("统计", false);

    }

    @Override
    protected void setUpData() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.service_fragment;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tj:
                Intent intent = new Intent(getActivity(), StatisticsActivity.class);
                intent.putExtra("title", "hj");
                startActivity(intent);
                break;
            case R.id.btn_fw:
                Intent intent1 = new Intent(getActivity(), StatisticsActivity.class);
                intent1.putExtra("title", "fw");
                startActivity(intent1);
                break;
        }
    }
}
