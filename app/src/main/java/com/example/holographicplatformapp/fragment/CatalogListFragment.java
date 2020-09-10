package com.example.holographicplatformapp.fragment;

import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.holographicplatformapp.R;
import com.example.holographicplatformapp.activity.BaseFragment;
import com.example.holographicplatformapp.activity.zyfl.ResourceTitleActivity;

/*
 * 目录页
 *
 * */
public class CatalogListFragment extends BaseFragment implements View.OnClickListener {
    private Button btnCatalog;//目录


    public static CatalogListFragment newInstance() {
        return new CatalogListFragment();
    }


    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData() {
        initToolbar("资源",false);

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.catalog_list_fragment;
    }

    @Override
    protected void init(View mContentView) {
        initView(mContentView);
    }

    private void initView(View view) {
        btnCatalog = view.findViewById(R.id.tv_mulu);


        btnCatalog.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_mulu:
                startActivity(new Intent(getActivity(), ResourceTitleActivity.class));
                break;
        }


    }
}
