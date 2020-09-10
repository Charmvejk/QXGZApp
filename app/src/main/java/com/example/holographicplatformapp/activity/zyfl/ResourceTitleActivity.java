package com.example.holographicplatformapp.activity.zyfl;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.holographicplatformapp.R;
import com.example.holographicplatformapp.activity.BaseActivity;
import com.example.holographicplatformapp.bean.ResourceBean;
import com.google.gson.Gson;

import java.util.List;

import static com.example.holographicplatformapp.Constants.db_regist_allname;
import static com.example.holographicplatformapp.HttpUrls.postXml;

/*
 * 资源分类
 * */
public class ResourceTitleActivity extends BaseActivity {
    private RecyclerView recyclerView;

    ResourceBean beans = null;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 666:
                    initRecycleView(beans);
                    break;
            }
        }

        ;
    };


    private void initRecycleView(ResourceBean beans) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

//设置Adapter
        recyclerView.setAdapter(new NormalAdapter(null, beans));
        //设置分隔线

//设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initView() {

        mToolbarTb.setTitle("资源分类");
        //执行网络请求

        new Thread() {
            @Override
            public void run() {
                String path = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                        "<paras>" +
                        "</paras>";
                String result = postXml(db_regist_allname, path);
                Gson gson = new Gson();
                beans = gson.fromJson(result, ResourceBean.class);
                Message message = new Message();
                message.what = 666;
                mHandler.sendMessage(message);
            }
        }.start();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_resource_title;
    }

    // ① 创建Adapter
    public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.VH> {
        //② 创建ViewHolder
        public class VH extends RecyclerView.ViewHolder {
            final TextView title;
            CardView mCardView;
            LinearLayout llContainer;

            public VH(View v) {
                super(v);
                title = (TextView) v.findViewById(R.id.tv_zymc);
                mCardView = v.findViewById(R.id.cardview);
                llContainer = v.findViewById(R.id.ll_container);
            }
        }

        private List<String> mDatas;
        private ResourceBean resourceBeans;

        public NormalAdapter(List<String> data, ResourceBean beans) {
            this.mDatas = data;
            resourceBeans = beans;
        }

        //③ 在Adapter中实现3个方法
        @Override
        public void onBindViewHolder(VH holder, int position) {
            if (!"全部".equals(resourceBeans.getData().get(position).getName())) {
                holder.title.setText(resourceBeans.getData().get(position).getName());
            } else {
                holder.mCardView.setVisibility(View.GONE);
            }
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //item 点击事件
                    Intent intent = new Intent(ResourceTitleActivity.this, ResourceDetailsActivity.class);
                    intent.putExtra("id", resourceBeans.getData().get(position).getDest_db());
                    intent.putExtra("title", resourceBeans.getData().get(position).getName());
                    startActivity(intent);

                }
            });
        }


        @Override
        public int getItemCount() {
            return resourceBeans.getData().size();
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            //LayoutInflater.from指定写法
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zyfl, parent, false);
            return new VH(v);
        }
    }

}
