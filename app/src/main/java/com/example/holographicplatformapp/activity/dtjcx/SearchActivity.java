package com.example.holographicplatformapp.activity.dtjcx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;

import com.example.holographicplatformapp.R;
import com.example.holographicplatformapp.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 多条件查询
 * wsy
 */

public class SearchActivity extends BaseActivity {
    private RecyclerView mRcSearch;

    @Override
    protected void initDatas() {
        mToolbarTb.setTitle("多级筛选");

    }

    @Override
    protected void initView() {
        mRcSearch = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//设置布局管理器
        mRcSearch.setLayoutManager(layoutManager);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_search;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("客户端名称");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {  // 点击软件盘搜索按钮会弹出 吐司

                return false;
            }

            // 搜索框文本改变事件
            @Override
            public boolean onQueryTextChange(String s) {
                // 文本内容是空就让 recyclerView 填充全部数据 // 可以是其他容器 如listView

                //匹配文字 变色SS

                return false;
            }
        });
        return true;

    }


}
