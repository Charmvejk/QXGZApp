package com.example.holographicplatformapp.activity.zyfl;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.holographicplatformapp.R;
import com.example.holographicplatformapp.activity.BaseActivity;
import com.example.holographicplatformapp.bean.ResourceDetailsBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.holographicplatformapp.Constants.table_page_fy_order;
import static com.example.holographicplatformapp.HttpUrls.postXml;

public class ResourceDetailsActivity extends BaseActivity {
    //列表id查询详情
    private String mId;
    private RecyclerView recyclerView;
    private ResourceDetailsBean beans = null;
    private List<Integer> mP;
    /**
     * 所有数据 可以是联网获取 如果有需要可以将其储存在数据库中 我们用简单的String做演示
     */
    private List<String> wholeList;
    /**
     * 此list用来保存符合我们规则的数据
     */
    private List<String> list;
    private NormalAdapter adapter;
    private int record = 0;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 666:
                    wholeList = new ArrayList<>();
                    list = new ArrayList<>();
                    initData();
                    initRecycleView(beans);

                    break;
            }
        }

        private void initData() {
            for (int i = 0; i < beans.getData().size(); i++) {
                wholeList.add(beans.getData().get(i).getTablecname());
            }
            list.addAll(wholeList);
        }

        ;
    };



    private void initNet() {
        //执行网络请求

        new Thread() {
            @Override
            public void run() {
                String path = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                        "<paras>\n" +
                        "<para><name>PCount</name > <sqldbtype>BigInt</sqldbtype><value>0</value></para>" +
                        "<para><name>RCount</name > <sqldbtype>BigInt</sqldbtype><value>0</value></para>" +
                        "<para><name>sys_Table</name > <sqldbtype>NVarChar</sqldbtype><value>view_table_list_release</value></para>" +
                        "<para><name>sys_Key</name > <sqldbtype>VarChar</sqldbtype><value>tableid</value></para>" +
                        "<para><name>sys_Fields</name > <sqldbtype>NVarChar</sqldbtype><value>tableid,dbname,tablename,tablecname,tablecode,abstract,src_dw,theme,name,operationdatetime,org_name,theme_name,table_rows</value></para>\n" +
                        "<para><name>sys_Where</name > <sqldbtype>NVarChar</sqldbtype><value>1=1" + " and " + "dbname=" + "'" + mId + "'" + "</value></para>" +
                        "<para><name>sys_Order</name > <sqldbtype>NVarChar</sqldbtype><value>tablename DESC</value></para>" +
                        "<para><name>sys_PageIndex</name > <sqldbtype>Int</sqldbtype><value>0</value></para> " +
                        " <para><name>sys_PageSize</name > <sqldbtype>Int</sqldbtype><value>500</value></para>" +
                        "" +
                        "</paras>";
                String result = postXml(table_page_fy_order, path);
                Gson gson = new Gson();
                beans = gson.fromJson(result, ResourceDetailsBean.class);
                Message message = new Message();
                message.what = 666;
                mHandler.sendMessage(message);
            }
        }.start();
    }


    private void initRecycleView(ResourceDetailsBean beans) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

//设置Adapter
        adapter = new NormalAdapter(null, beans, list);
        recyclerView.setAdapter(adapter);
        //设置分隔线

//设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initView() {

         if (getIntent().getStringExtra("id") != null) {
            mId = getIntent().getStringExtra("id");
            mToolbarTb.setTitle(getIntent().getStringExtra("title"));
        }
        initNet();


    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_resource_details;
    }

    // ① 创建Adapter
    public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.VH> {
        //② 创建ViewHolder
        public class VH extends RecyclerView.ViewHolder {
            final TextView title;
            private TextView tvCount;
            private TextView tvDate;
            private LinearLayout llContaner;
            private TextView tvNumber;

            /**
             * 需要改变颜色的text
             */


            public VH(View v) {
                super(v);
                title = (TextView) v.findViewById(R.id.tv_title);
                tvCount = (TextView) v.findViewById(R.id.tv_count);
                tvDate = (TextView) v.findViewById(R.id.tv_date);
                tvNumber = (TextView) v.findViewById(R.id.tv_xuhao);
                llContaner = (LinearLayout) v.findViewById(R.id.ll_continar);

            }

        }

        private String text;
        private List<String> list = new ArrayList<>();

        /**
         * 在MainActivity中设置text
         */
        public void setText(String text) {
            this.text = text;
        }

        private List<String> mDatas;
        private ResourceDetailsBean resourceBeans;

        public NormalAdapter(List<String> data, ResourceDetailsBean beans, List<String> list) {
            this.mDatas = data;
            resourceBeans = beans;
            this.list = list;
        }

        //③ 在Adapter中实现3个方法
        @Override
        public void onBindViewHolder(NormalAdapter.VH holder, int position) {
            /**如果没有进行搜索操作或者搜索之后点击了删除按钮 我们会在MainActivity中把text置空并传递过来*/
            if (text != null) {
                //设置span
                SpannableString string = matcherSearchText(Color.rgb(255, 0, 0), list.get(position), text);
                holder.title.setText(string);
                holder.tvCount.setText("" + resourceBeans.getData().get(mP.get(position)).getTable_rows());
                String date = resourceBeans.getData().get(mP.get(position)).getOperationdatetime();

                holder.tvDate.setText(date.substring(0, date.indexOf("T")));

            } else {
                holder.title.setText(list.get(position));
                holder.tvCount.setText("" + resourceBeans.getData().get(position).getTable_rows());
                String date = resourceBeans.getData().get(position).getOperationdatetime();

                holder.tvDate.setText(date.substring(0, date.indexOf("T")));

//                holder.title.setText(resourceBeans.getData().get(position).getTablecname());
            }
            holder.tvNumber.setText("" + (position + 1));

            if (resourceBeans.getData().size() > 1) {

                if (position % 2 == 0) {
                    holder.llContaner.setBackgroundColor(getResources().getColor(R.color.light_light_gray));
                } else {
                    holder.llContaner.setBackgroundColor(getResources().getColor(R.color.white));
                }

            } else {


            }
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            //LayoutInflater.from指定写法
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zyfl_details, parent, false);
            return new VH(v);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);


        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("信息资源名称");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {  // 点击软件盘搜索按钮会弹出 吐司

                return false;
            }

            // 搜索框文本改变事件
            @Override
            public boolean onQueryTextChange(String s) {
                // 文本内容是空就让 recyclerView 填充全部数据 // 可以是其他容器 如listView

                //匹配文字 变色
                doChangeColor(s);
                return false;
            }
        });
        return true;

    }

    /**
     * 正则匹配 返回值是一个SpannableString 即经过变色处理的数据
     */
    private SpannableString matcherSearchText(int color, String text, String keyword) {
        SpannableString spannableString = new SpannableString(text);
        //条件 keyword
        Pattern pattern = Pattern.compile(keyword);
        //匹配
        Matcher matcher = pattern.matcher(spannableString);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            //ForegroundColorSpan 需要new 不然也只能是部分变色
            spannableString.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        //返回变色处理的结果
        return spannableString;
    }

    /**
     * 刷新UI
     */
    private void refreshUI() {
        if (adapter == null) {
            adapter = new NormalAdapter(null, null, list);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 字体匹配方法
     */
    private void doChangeColor(String text) {
        //clear是必须的 不然只要改变edittext数据，list会一直add数据进来
        list.clear();
        record = 0;
        //不需要匹配 把所有数据都传进来 不需要变色
        if (text.equals("")) {
            list.addAll(wholeList);
            //防止匹配过文字之后点击删除按钮 字体仍然变色的问题
            adapter.setText(null);
            refreshUI();
        } else {
            mP = new ArrayList<>();
            //如果edittext里面有数据 则根据edittext里面的数据进行匹配 用contains判断是否包含该条数据 包含的话则加入到list中
            for (String i : wholeList) {
                if (i.contains(text)) {
                    list.add(i);
                    mP.add(record);

                }
                record += 1;
            }
            //设置要变色的关键字
            adapter.setText(text);
            refreshUI();
        }
    }
}
