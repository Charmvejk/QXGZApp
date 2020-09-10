package com.example.holographicplatformapp.activity.tj;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.holographicplatformapp.R;
import com.example.holographicplatformapp.activity.BaseActivity;
import com.example.holographicplatformapp.activity.cx.QueryDetailsActivity;


import java.util.ArrayList;
import java.util.List;

import static com.example.holographicplatformapp.Constants.cx_fw_dwny;
import static com.example.holographicplatformapp.Constants.cx_fw_resourceny;
import static com.example.holographicplatformapp.Constants.cx_hj_clientny;
import static com.example.holographicplatformapp.Constants.cx_hj_dwny;
import static com.example.holographicplatformapp.Constants.cx_hj_resourceny;
import static com.example.holographicplatformapp.Constants.tj_fw_clientall;
import static com.example.holographicplatformapp.Constants.tj_fw_clientny;
import static com.example.holographicplatformapp.Constants.tj_fw_dwall;
import static com.example.holographicplatformapp.Constants.tj_fw_dwny;
import static com.example.holographicplatformapp.Constants.tj_fw_resourceall;
import static com.example.holographicplatformapp.Constants.tj_fw_resourceny;
import static com.example.holographicplatformapp.Constants.tj_hj_clientall;
import static com.example.holographicplatformapp.Constants.tj_hj_clientny;
import static com.example.holographicplatformapp.Constants.tj_hj_dwall;
import static com.example.holographicplatformapp.Constants.tj_hj_dwny;
import static com.example.holographicplatformapp.Constants.tj_hj_resourceall;
import static com.example.holographicplatformapp.Constants.tj_hj_resourceny;

/*
 *
 * 统计页面2部分 （汇聚、服务）
 *
 * */
public class StatisticsActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private List<String> mList;


    @Override
    protected void initDatas() {

        mList = new ArrayList<>();
        if ("hj".equals(getIntent().getStringExtra("title"))) {
            mToolbarTb.setTitle("汇聚统计");
            mList.add("汇聚按客户端统计");
            mList.add("汇聚按资源统计");
            mList.add("汇聚按单位统计");
            mList.add("汇聚按客户端查询");
            mList.add("汇聚按资源查询");
            mList.add("汇聚按单位查询");
        } else {
            mToolbarTb.setTitle("服务统计");
            mList.add("服务按客户端统计");
            mList.add("服务按资源统计");
            mList.add("服务按单位统计");
            mList.add("服务按客户端查询");
            mList.add("服务按资源查询");
            mList.add("服务按单位查询");
        }
        initRecycleView();


    }

    @Override
    protected void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

    }

    private void initRecycleView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

//设置Adapter
        recyclerView.setAdapter(new NormalAdapter(mList));
        //设置分隔线

//设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_statistics;
    }


    // ① 创建Adapter
    public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.VH> {
        //② 创建ViewHolder
        public class VH extends RecyclerView.ViewHolder {
            final TextView title;

            CardView mCardView;


            public VH(View v) {
                super(v);
                title = (TextView) v.findViewById(R.id.tv_zymc);
                mCardView = v.findViewById(R.id.cardview);

            }
        }


        List<String> mList = new ArrayList<>();

        public NormalAdapter(List<String> data) {

            mList = data;

        }

        //③ 在Adapter中实现3个方法
        @Override
        public void onBindViewHolder(VH holder, int position) {
            holder.title.setText(mList.get(position));
            List<String> mListUrl = new ArrayList<>();
            List<String> mListDayUrl = new ArrayList<>();
            if ("hj".equals(getIntent().getStringExtra("title"))) {
                mListUrl.add(tj_hj_clientall);
                mListUrl.add(tj_hj_resourceall);
                mListUrl.add(tj_hj_dwall);
                mListUrl.add(cx_hj_clientny);
                mListUrl.add(cx_hj_resourceny);
                mListUrl.add(cx_hj_dwny);

                mListDayUrl.add(tj_hj_clientny);
                mListDayUrl.add(tj_hj_resourceny);
                mListDayUrl.add(tj_hj_dwny);
                mListDayUrl.add(cx_hj_clientny);
                mListDayUrl.add(cx_hj_resourceny);
                mListDayUrl.add(cx_hj_dwny);

            } else {
                mListUrl.add(tj_fw_clientall);
                mListUrl.add(tj_fw_resourceall);
                mListUrl.add(tj_fw_dwall);
                mListUrl.add("");
                mListUrl.add(cx_fw_resourceny);
                mListUrl.add(cx_fw_dwny);

                mListDayUrl.add(tj_fw_clientny);
                mListDayUrl.add(tj_fw_resourceny);
                mListDayUrl.add(tj_fw_dwny);
                mListDayUrl.add("");
                mListDayUrl.add(cx_fw_resourceny);
                mListDayUrl.add(cx_fw_dwny);
            }

            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nextPages(mList.get(position), mListUrl.get(position),mListDayUrl.get(position));
                }


            });

        }


        @Override
        public int getItemCount() {
            return mList.size();
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            //LayoutInflater.from指定写法
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zyfl, parent, false);
            return new VH(v);
        }
    }

    private void nextPages(String title, String url,String mDayUrl) {
        Intent intent;
        if (title.contains("查询")) {
            intent = new Intent(StatisticsActivity.this, QueryDetailsActivity.class);

        } else {
            intent = new Intent(StatisticsActivity.this, CountDetailsActivity.class);
        }
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        intent.putExtra("dayUrl", mDayUrl);
        startActivity(intent);

    }
}
