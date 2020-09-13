package com.example.holographicplatformapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.holographicplatformapp.R;

/**
 * * Created by wsy on 20/09/09.
 */
public abstract class BaseFragment extends Fragment {

    private View mContentView;


    private Activity mActivity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != mContentView) {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (null != parent) {
                parent.removeView(mContentView);
            }
        } else {
            mActivity = getActivity();
            mContentView = inflater.inflate(setLayoutResourceID(), container, false);
            init(mContentView);
            setHasOptionsMenu(true);
            setUpView();
            setUpData();
        }

        return mContentView;

    }


    public Toolbar initToolbar(String title, boolean i) {
        AppCompatActivity mAppCompatActivity = (AppCompatActivity) mActivity;
        Toolbar toolbar = (Toolbar) mContentView.findViewById(R.id.tb_toolbar);
        mAppCompatActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(i);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(title);
        }
        return toolbar;
    }

    /**
     * 此方法用于初始化成员变量及获取Intent传递过来的数据 * 注意：这个方法中不能调用所有的View，因为View还没有被初始化，要使用View在initView方法中调用
     *
     * @param mContentView
     */

    protected abstract void init(View mContentView);

    /**
     * 一些View的相关操作
     */
    protected abstract void setUpView();

    /**
     * 一些Data的相关操作
     */
    protected abstract void setUpData();

    /**
     * 此方法用于返回Fragment设置ContentView的布局文件资源ID * * @return 布局文件资源ID
     */
    protected abstract int setLayoutResourceID();


}
