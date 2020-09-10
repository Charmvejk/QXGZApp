package com.example.holographicplatformapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.holographicplatformapp.R;
import com.example.holographicplatformapp.fragment.CatalogListFragment;
import com.example.holographicplatformapp.fragment.MainFragment;
import com.example.holographicplatformapp.fragment.ServiceFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

//
//      ┏┛ ┻━━━━━┛ ┻┓
//      ┃　　　　　　 ┃
//      ┃　　　━　　　┃
//      ┃　┳┛　  ┗┳　┃
//      ┃　　　　　　 ┃
//      ┃　　　┻　　　┃
//      ┃　　　　　　 ┃
//      ┗━┓　　　┏━━━┛
//        ┃　　　┃   神兽保佑
//        ┃　　　┃   代码无BUG！
//        ┃　　　┗━━━━━━━━━┓
//        ┃　　　　　　　    ┣┓
//        ┃　　　　         ┏┛
//        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
//          ┃ ┫ ┫   ┃ ┫ ┫
//          ┗━┻━┛   ┗━┻━┛
/*
 * 主页--管理fragments
 * */
public class MainActivity extends BaseActivity {
    private ArrayList<Fragment> framents = new ArrayList<Fragment>();
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;
    private int mCurrent = -1;
    private BottomNavigationView bnv_menu;


    @Override
    protected void initDatas() {
        framents.add(CatalogListFragment.newInstance());
        framents.add(MainFragment.newInstance());
        framents.add(ServiceFragment.newInstance());
        fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < framents.size(); i++) {
            Fragment fragment = framents.get(i);
            fragmentTransaction.add(R.id.main_frame, fragment);
            fragmentTransaction.hide(fragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
        //默认首页
        switchFragment(1);

        bnv_menu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void initView() {
        bnv_menu = findViewById(R.id.bnv_menu);
        bnv_menu.setSelectedItemId(bnv_menu.getMenu().getItem(1).getItemId());
    }


    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_me:
                    switchFragment(0);
                    return true;
                case R.id.action_home:
                    switchFragment(1);
                    return true;
                case R.id.action_explore:
                    switchFragment(2);
                    return true;

            }
            return false;
        }
    };

    private void switchFragment(int index) {
        if (index != mCurrent) {
            fragmentTransaction = fragmentManager.beginTransaction();
            for (int i = 0; i < framents.size(); i++) {
                Fragment fragment = framents.get(i);
                if (index == i) {
                    fragmentTransaction.show(fragment);
                }
                if (i == mCurrent) {
                    fragmentTransaction.hide(fragment);
                }
            }
            fragmentTransaction.commit();
            mCurrent = index;
        }
    }
}
