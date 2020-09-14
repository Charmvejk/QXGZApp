package com.example.holographicplatformapp.activity.cx;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import com.example.holographicplatformapp.R;
import com.example.holographicplatformapp.activity.BaseActivity;
import com.example.holographicplatformapp.activity.dtjcx.SearchActivity;
import com.example.holographicplatformapp.activity.tj.CountDetailsActivity;
import com.example.holographicplatformapp.adapter.AbsCommonAdapter;
import com.example.holographicplatformapp.adapter.AbsViewHolder;
import com.example.holographicplatformapp.bean.OnlineSaleBean;
import com.example.holographicplatformapp.bean.TableModel;
import com.example.holographicplatformapp.bean.fwDwBean;
import com.example.holographicplatformapp.bean.fwDwCXBean;
import com.example.holographicplatformapp.bean.fwKHDBean;
import com.example.holographicplatformapp.bean.fwNameBeans;
import com.example.holographicplatformapp.bean.fwZYCXBean;
import com.example.holographicplatformapp.bean.hjZYCXBean;
import com.example.holographicplatformapp.bean.hjZYDLBean;
import com.example.holographicplatformapp.dialog.CustomProgressDialog;
import com.example.holographicplatformapp.dialog.DoubleDatePickerDialog;
import com.example.holographicplatformapp.scrrow.SyncHorizontalScrollView;
import com.github.promeg.pinyinhelper.Pinyin;
import com.google.gson.Gson;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.jwenfeng.library.pulltorefresh.ViewStatus;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import static com.example.holographicplatformapp.Constants.ydd_cx_fwmc;
import static com.example.holographicplatformapp.Constants.ydd_cx_fwzydl;
import static com.example.holographicplatformapp.Constants.ydd_cx_hjzyxl;
import static com.example.holographicplatformapp.Constants.ydd_hj_cxzydl;
import static com.example.holographicplatformapp.HttpUrls.postXml;

public class QueryDetailsActivity extends BaseActivity {
    private fwKHDBean beans;
    private hjZYCXBean hjZYCXBean;
    private hjZYDLBean hjZYDLBean;
    private fwDwBean fwDwBean;
    private fwZYCXBean fwZYCXBean;
    private fwNameBeans fwNameBeans;
    private fwDwCXBean fwDwCXBean;
    private final int selectCode = 678;
    private final int select2Code = 679;
    private String niceType = "1";
    private int type = 1;
    private NiceSpinner spinner_zydl;
    private NiceSpinner spinner_zyxl;
    private NiceSpinner spinner_main_zydl;
    /**
     * 用于存放标题的id,与textview引用
     */

    //表格部分
    private TextView tv_table_title_left;
    private LinearLayout right_title_container;
    private ListView leftListView;
    private ListView rightListView;
    private AbsCommonAdapter<TableModel> mLeftAdapter, mRightAdapter;
    private SyncHorizontalScrollView titleHorScv;
    private SyncHorizontalScrollView contentHorScv;
    private CustomProgressDialog customProgressDialog;
    private PullToRefreshLayout pullToRefreshLayout;//刷新
    /*主要筛选过滤*/
    private int record = 0;
    private boolean isRefresh = false;
    private List<Integer> mP = new ArrayList<>();
    private List<OnlineSaleBean> onlineSaleBeanList = new ArrayList<>();
    private List<String> datasetZYDL;
    private List<String> datasetZYXL;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {

                case 666:
                    onlineSaleBeanList.clear();
                    for (int i = 0; i < beans.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(beans.getData().get(i).getSystemname()));
                    }
                    mP.clear();
                    if (onlineSaleBeanList.size() == 0) {
                        customProgressDialog.dismiss();
                        pullToRefreshLayout.showView(ViewStatus.EMPTY_STATUS);
                    } else {
                        pullToRefreshLayout.showView(ViewStatus.CONTENT_STATUS);
                    }
                    setDatas(onlineSaleBeanList, mP, Integer.parseInt(niceType));

                    break;
                case 999:
                    onlineSaleBeanList.clear();
                    for (int i = 0; i < hjZYCXBean.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(hjZYCXBean.getData().get(i).getName()));
                    }
                    mP.clear();
                    if (onlineSaleBeanList.size() == 0) {
                        customProgressDialog.dismiss();
                        pullToRefreshLayout.showView(ViewStatus.EMPTY_STATUS);
                    } else {
                        pullToRefreshLayout.showView(ViewStatus.CONTENT_STATUS);
                    }
                    setDatas(onlineSaleBeanList, mP, Integer.parseInt(niceType));
                    break;
                case 000:
                    onlineSaleBeanList.clear();
                    for (int i = 0; i < fwDwBean.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(fwDwBean.getData().get(i).getName()));
                    }
                    mP.clear();
                    if (onlineSaleBeanList.size() == 0) {
                        customProgressDialog.dismiss();
                        pullToRefreshLayout.showView(ViewStatus.EMPTY_STATUS);
                    } else {
                        pullToRefreshLayout.showView(ViewStatus.CONTENT_STATUS);
                    }
                    setDatas(onlineSaleBeanList, mP, Integer.parseInt(niceType));
                    break;
                case 1000:
                    onlineSaleBeanList.clear();
                    for (int i = 0; i < fwZYCXBean.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(fwZYCXBean.getData().get(i).getDbcname()));
                    }
                    mP.clear();
                    if (onlineSaleBeanList.size() == 0) {
                        customProgressDialog.dismiss();
                        pullToRefreshLayout.showView(ViewStatus.EMPTY_STATUS);
                    } else {
                        pullToRefreshLayout.showView(ViewStatus.CONTENT_STATUS);
                    }
                    setDatas(onlineSaleBeanList, mP, Integer.parseInt(niceType));
                    break;
                case 6000:
                    onlineSaleBeanList.clear();
                    for (int i = 0; i < fwDwCXBean.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(fwDwCXBean.getData().get(i).getName()));
                    }
                    mP.clear();
                    if (onlineSaleBeanList.size() == 0) {
                        pullToRefreshLayout.showView(ViewStatus.EMPTY_STATUS);
                        customProgressDialog.dismiss();
                    } else {
                        pullToRefreshLayout.showView(ViewStatus.CONTENT_STATUS);
                    }
                    setDatas(onlineSaleBeanList, mP, Integer.parseInt(niceType));
                    break;
                case 9899:
                    datasetZYDL = new ArrayList<>();
                    datasetZYDL.add("资源大类");
                    for (int i = 0; i < hjZYDLBean.getData().size(); i++) {
                        datasetZYDL.add(hjZYDLBean.getData().get(i).getDbcname());
                    }

                    spinner_zydl.attachDataSource(datasetZYDL);
                    spinner_zydl.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
                        @Override
                        public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                            String item = parent.getItemAtPosition(position).toString();
                            spinner_zyxl.setClickable(true);
                            initZYXL();
                        }
                    });


                    break;

                case 11000:
                    datasetZYDL = new ArrayList<>();
                    datasetZYDL.add("资源大类");
                    for (int i = 0; i < hjZYDLBean.getData().size(); i++) {
                        datasetZYDL.add(hjZYDLBean.getData().get(i).getName());
                    }

                    spinner_main_zydl.attachDataSource(datasetZYDL);
                    spinner_main_zydl.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
                        @Override
                        public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                            String item = parent.getItemAtPosition(position).toString();
                            spinner_zydl.setClickable(true);
                            initZYXL();

                        }
                    });


                    break;
                case 19899:
                    datasetZYXL = new ArrayList<>();

                    for (int i = 0; i < hjZYDLBean.getData().size(); i++) {
                        datasetZYXL.add(hjZYDLBean.getData().get(i).getTablecname());
                    }

                    spinner_zyxl.attachDataSource(datasetZYXL);
                    spinner_zyxl.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
                        @Override
                        public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                            String item = parent.getItemAtPosition(position).toString();

                        }
                    });


                    break;
                case 1111000:
                    datasetZYXL = new ArrayList<>();

                    for (int i = 0; i < fwNameBeans.getData().size(); i++) {
                        datasetZYXL.add(fwNameBeans.getData().get(i).getProc_cname());
                    }

                    spinner_zydl.attachDataSource(datasetZYXL);
                    spinner_zydl.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
                        @Override
                        public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                            String item = parent.getItemAtPosition(position).toString();

                        }
                    });


                    break;
            }
        }

        ;
    };


    @Override
    protected void initDatas() {
        initNet(1);
        mToolbarTb.setTitle(getIntent().getStringExtra("title"));

    }

    private void initZYXL() {
        /*
         * 查询 汇聚、服务资源小类
         * */
        new Thread() {
            @Override
            public void run() {
                String result;
                Message message = new Message();
                Gson gson = new Gson();
                String path = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?> " +
                        "<paras> " +

                        "</paras>";
                try {
                    switch (getIntent().getStringExtra("title")) {

                        case "汇聚按资源查询":

                            String result2 = postXml(ydd_cx_hjzyxl, path);
                            hjZYDLBean = gson.fromJson(result2, hjZYDLBean.class);

                            message.what = 19899;
                            mHandler.sendMessage(message);

                            break;
                        case "服务按资源查询":
                            result = postXml(ydd_cx_fwmc, path);
                            fwNameBeans = gson.fromJson(result, fwNameBeans.class);

                            message.what = 1111000;
                            mHandler.sendMessage(message);
                            break;

                        default:
                            throw new IllegalStateException("Unexpected value: " + getIntent().getStringExtra("title"));
                    }


                } catch (Exception e) {

                }

            }
        }.start();
    }

    private void initNet(int i) {


        if (i == 1) {
            customProgressDialog = new CustomProgressDialog(QueryDetailsActivity.this, "");
            customProgressDialog.show();
        }
        ArrayList<String> mListUrlPath = new ArrayList<>();
        mListUrlPath.add("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                "<paras>" +
                "<para><name>clientid</name > <sqldbtype>Int</sqldbtype><value>10000001</value></para> " +
                "<para><name>ks</name > <sqldbtype>Int</sqldbtype><value>201912</value></para> " +
                " <para><name>js</name > <sqldbtype>Int</sqldbtype><value>202004</value></para>" +
                "</paras>");
        mListUrlPath.add("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                "<paras>" +
                "<para><name>dbid</name > <sqldbtype>Int</sqldbtype><value>7</value></para>" +
                "<para><name>tableid</name > <sqldbtype>Int</sqldbtype><value>19</value></para>" +
                "<para><name>ks</name > <sqldbtype>Int</sqldbtype><value>201912</value></para>" +
                " <para><name>js</name > <sqldbtype>Int</sqldbtype><value>202004</value></para>" +
                "</paras>");
        mListUrlPath.add("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                "<paras>" +
                "<para><name>deptid</name > <sqldbtype>Int</sqldbtype><value>5</value></para>" +
                "<para><name>ks</name > <sqldbtype>Int</sqldbtype><value>201912</value></para>" +
                " <para><name>js</name > <sqldbtype>Int</sqldbtype><value>202004</value></para>" +
                "</paras>");
        mListUrlPath.add("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                "<paras>" +
                "<para><name>dbid</name > <sqldbtype>Int</sqldbtype><value>1</value></para>" +
                "<para><name>request_fwid</name > <sqldbtype>Int</sqldbtype><value>-1</value></para>" +
                "<para><name>ks</name > <sqldbtype>Int</sqldbtype><value>201912</value></para>" +
                " <para><name>js</name > <sqldbtype>Int</sqldbtype><value>202004</value></para>" +
                "</paras>");
        mListUrlPath.add("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                "<paras>" +
                "<para><name>deptid</name > <sqldbtype>Int</sqldbtype><value>5</value></para>" +
                "<para><name>ks</name > <sqldbtype>Int</sqldbtype><value>201912</value></para>" +
                " <para><name>js</name > <sqldbtype>Int</sqldbtype><value>202004</value></para>" +
                "</paras>");
        mListUrlPath.add("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                "<paras>" +
                "</paras>");

        new Thread() {
            @Override
            public void run() {
                String result;
                Message message = new Message();
                Gson gson = new Gson();
                try {
                    switch (getIntent().getStringExtra("title")) {
                        case "汇聚按客户端查询":
                            result = postXml(getIntent().getStringExtra("url"), mListUrlPath.get(0));
                            beans = gson.fromJson(result, fwKHDBean.class);

                            message.what = 666;
                            mHandler.sendMessage(message);
                            break;
                        case "汇聚按资源查询":
                            result = postXml(getIntent().getStringExtra("url"), mListUrlPath.get(1));
                            hjZYCXBean = gson.fromJson(result, hjZYCXBean.class);

                            message.what = 999;
                            mHandler.sendMessage(message);

                            break;
                        case "汇聚按单位查询":
                            result = postXml(getIntent().getStringExtra("url"), mListUrlPath.get(2));
                            fwDwBean = gson.fromJson(result, fwDwBean.class);

                            message.what = 000;
                            mHandler.sendMessage(message);
                            break;
                        case "服务按资源查询":
                            result = postXml(getIntent().getStringExtra("url"), mListUrlPath.get(3));
                            fwZYCXBean = gson.fromJson(result, fwZYCXBean.class);

                            message.what = 1000;
                            mHandler.sendMessage(message);
                            break;
                        case "服务按单位查询":
                            result = postXml(getIntent().getStringExtra("url"), mListUrlPath.get(4));
                            fwDwCXBean = gson.fromJson(result, fwDwCXBean.class);

                            message.what = 6000;
                            mHandler.sendMessage(message);
                            break;
                    }


                } catch (Exception e) {

                }

            }
        }.start();
        /*
         * 查询 汇聚、服务资源大类
         * */
        new Thread() {
            @Override
            public void run() {
                String result;
                Message message = new Message();
                Gson gson = new Gson();
                try {
                    switch (getIntent().getStringExtra("title")) {

                        case "汇聚按资源查询":
                            String result2 = postXml(ydd_hj_cxzydl, mListUrlPath.get(5));
                            hjZYDLBean = gson.fromJson(result2, hjZYDLBean.class);

                            message.what = 9899;
                            mHandler.sendMessage(message);

                            break;
                        case "服务按资源查询":
                            result = postXml(ydd_cx_fwzydl, mListUrlPath.get(5));
                            hjZYDLBean = gson.fromJson(result, hjZYDLBean.class);

                            message.what = 11000;
                            mHandler.sendMessage(message);
                            break;

                    }


                } catch (Exception e) {

                }

            }
        }.start();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select_type2, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        if ("服务按资源查询".equals(getIntent().getStringExtra("title"))) {
            searchView.setQueryHint("资源大类名称");
        } else {
            searchView.setQueryHint(tv_table_title_left.getText().toString());
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {  // 点击软件盘搜索按钮会弹出 吐司

                return false;
            }

            // 搜索框文本改变事件
            @Override
            public boolean onQueryTextChange(String s) {
                // 文本内容是空就让 recyclerView 填充全部数据 // 可以是其他容器 如listView

                filterData(s);
                return false;
            }
        });
        return true;
    }

    /**
     * 根据输入框中的值来过滤数据并更新RecyclerView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<OnlineSaleBean> filterDateList = new ArrayList<>();

        if (TextUtils.isEmpty(filterStr)) {
            mP.clear();
            record = 0;
            filterDateList = onlineSaleBeanList;
        } else {
            filterDateList.clear();
            mP.clear();
            record = 0;
            for (OnlineSaleBean sortModel : onlineSaleBeanList) {
                String name = sortModel.getCompanyName();
                /**
                 * 后期首字母
                 */
                if (name.indexOf(filterStr.toString()) != -1 ||
                        Pinyin.toPinyin(name, "/").startsWith(filterStr.toString())

                ) {
                    filterDateList.add(sortModel);
                    mP.add(record);

                }
                record += 1;
            }
        }

        setDatas(filterDateList, mP, type);
        mLeftAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initView() {
        tv_table_title_left = (TextView) findViewById(R.id.tv_table_title_left);
        spinner_main_zydl = findViewById(R.id.nice_spinner_zydl);
        pullToRefreshLayout = findViewById(R.id.pull_refresh);
        leftListView = (ListView) findViewById(R.id.left_container_listview);
        rightListView = (ListView) findViewById(R.id.right_container_listview);
        right_title_container = (LinearLayout) findViewById(R.id.right_title_container);

        View view = getLayoutInflater().inflate(R.layout.table_right_title, right_title_container);
        pullToRefreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                niceType = "1";
                type = 1;
                initNet(2);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isRefresh) {

                            pullToRefreshLayout.finishRefresh();
                            isRefresh = false;
                        }
                    }
                }, 1000);
            }

            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshLayout.finishLoadMore();
                    }
                }, 1000);

            }
        });


        TextView tv_table_title_0 = view.findViewById(R.id.tv_table_title_0);
        TextView tv_table_title_1 = view.findViewById(R.id.tv_table_title_1);
        TextView tv_table_title_2 = view.findViewById(R.id.tv_table_title_2);
        TextView tv_table_title_3 = view.findViewById(R.id.tv_table_title_3);
        TextView tv_table_title_4 = view.findViewById(R.id.tv_table_title_4);
        spinner_zydl = view.findViewById(R.id.nice_spinner_zydl);
        spinner_zyxl = view.findViewById(R.id.nice_spinner_zyxl);
        List<String> mlist = new ArrayList<>();
        mlist.clear();

        switch (getIntent().getStringExtra("title")) {

            case "汇聚按客户端查询":
                tv_table_title_left.setText("客户端名称");


                mlist.add("单位名称");
                mlist.add("资源大类");
                mlist.add("资源小类");
                mlist.add("月份");
                mlist.add("数量");
                refreshUI(mlist, tv_table_title_0, tv_table_title_1, tv_table_title_2, tv_table_title_3, tv_table_title_4);

                break;
            case "汇聚按资源查询":
                tv_table_title_left.setText("单位名称");
                tv_table_title_0.setVisibility(View.GONE);
                spinner_zydl.setVisibility(View.VISIBLE);
                tv_table_title_1.setVisibility(View.GONE);
                spinner_zyxl.setVisibility(View.VISIBLE);
                spinner_zyxl.setClickable(false);

                mlist.add("资源大类");
                mlist.add("资源小类");
                mlist.add("月份");
                mlist.add("数量");


                refreshUI(mlist, tv_table_title_0, tv_table_title_1, tv_table_title_2, tv_table_title_3, tv_table_title_4);

                break;
            case "汇聚按单位查询":
                tv_table_title_left.setText("单位名称");

                mlist.add("资源大类");
                mlist.add("资源小类");
                mlist.add("月份");
                mlist.add("数量");
                refreshUI(mlist, tv_table_title_0, tv_table_title_1, tv_table_title_2, tv_table_title_3, tv_table_title_4);

                break;
            case "服务按资源查询":
                tv_table_title_left.setVisibility(View.GONE);
                spinner_main_zydl.setVisibility(View.VISIBLE);
                tv_table_title_0.setVisibility(View.GONE);
                spinner_zydl.setVisibility(View.VISIBLE);
                spinner_zydl.setClickable(false);
                spinner_zydl.setText("服务名称");
                mlist.add("服务名称");
                mlist.add("单位名称");
                mlist.add("月份");
                mlist.add("数量");


//                tv_table_title_0.setVisibility(View.GONE);
//                spinner_zydl.setVisibility(View.VISIBLE);

                refreshUI(mlist, tv_table_title_0, tv_table_title_1, tv_table_title_2, tv_table_title_3, tv_table_title_4);

                break;
            case "服务按单位查询":
                tv_table_title_left.setText("单位名称");

                mlist.add("服务名称");
                mlist.add("资源大类");
                mlist.add("月份");
                mlist.add("数量");
                refreshUI(mlist, tv_table_title_0, tv_table_title_1, tv_table_title_2, tv_table_title_3, tv_table_title_4);

                break;
        }

        titleHorScv = (SyncHorizontalScrollView) findViewById(R.id.title_horsv);
        contentHorScv = (SyncHorizontalScrollView) findViewById(R.id.content_horsv);
        // 设置两个水平控件的联动
        titleHorScv.setScrollView(contentHorScv);
        contentHorScv.setScrollView(titleHorScv);

        initTableView();
    }

    private void refreshUI(List<String> mlist, TextView tv_table_title_0, TextView tv_table_title_1, TextView tv_table_title_2, TextView tv_table_title_3, TextView tv_table_title_4) {
        if (mlist.size() == 5) {
            tv_table_title_0.setText(mlist.get(0));
            tv_table_title_1.setText(mlist.get(1));
            tv_table_title_2.setText(mlist.get(2));
            tv_table_title_3.setText(mlist.get(3));
            tv_table_title_4.setText(mlist.get(4));
        } else if (mlist.size() == 4) {
            tv_table_title_0.setText(mlist.get(0));
            tv_table_title_1.setText(mlist.get(1));
            tv_table_title_2.setText(mlist.get(2));
            tv_table_title_3.setText(mlist.get(3));
            tv_table_title_4.setVisibility(View.GONE);
        }


    }

    private void initTableView() {
        mLeftAdapter = new AbsCommonAdapter<TableModel>(mContext, R.layout.table_left_item) {
            @Override
            public void convert(AbsViewHolder helper, TableModel item, int pos) {
                TextView tv_table_content_left = helper.getView(R.id.tv_table_content_item_left);

                int count = item.getLeftTitle().length();

                TextView tv_xuhao = helper.getView(R.id.tv_bianhao);
                tv_table_content_left.setText(item.getLeftTitle());
                tv_xuhao.setText(item.getXuhao());
            }
        };
        mRightAdapter = new AbsCommonAdapter<TableModel>(mContext, R.layout.table_right_item) {
            @Override
            public void convert(AbsViewHolder helper, TableModel item, int pos) {
                TextView tv_table_content_right_item0 = helper.getView(R.id.tv_table_content_right_item0);
                TextView tv_table_content_right_item1 = helper.getView(R.id.tv_table_content_right_item1);
                TextView tv_table_content_right_item2 = helper.getView(R.id.tv_table_content_right_item2);
                TextView tv_table_content_right_item3 = helper.getView(R.id.tv_table_content_right_item3);
                TextView tv_table_content_right_item4 = helper.getView(R.id.tv_table_content_right_item4);
                switch (getIntent().getStringExtra("title")) {
                    case "汇聚按客户端查询":
                        tv_table_content_right_item4.setVisibility(View.VISIBLE);
                        break;
                    case "汇聚按资源查询":
                        tv_table_content_right_item4.setVisibility(View.GONE);
                        break;
                    case "汇聚按单位查询":
                        tv_table_content_right_item4.setVisibility(View.GONE);
                        break;
                    case "服务按资源查询":
                        tv_table_content_right_item4.setVisibility(View.GONE);
                        break;
                    case "服务按单位查询":
                        tv_table_content_right_item4.setVisibility(View.GONE);
                        break;
                }


                tv_table_content_right_item0.setText(item.getText0());
                tv_table_content_right_item1.setText(item.getText1());
                tv_table_content_right_item2.setText(item.getText2());
                tv_table_content_right_item3.setText(item.getText3());
                tv_table_content_right_item4.setText(item.getText4());


                //部分行设置颜色凸显
                item.setTextColor(tv_table_content_right_item0, item.getText0());
//                item_tj.setTextColor(tv_table_content_right_item5, item_tj.getText5());


            }
        };
        leftListView.setAdapter(mLeftAdapter);
        rightListView.setAdapter(mRightAdapter);
    }

    private void setDatas(List<OnlineSaleBean> onlineSaleBeanList, List<Integer> mP, int type) {

        List<TableModel> mDatas = new ArrayList<>();
        for (int i = 0; i < onlineSaleBeanList.size(); i++) {
            OnlineSaleBean onlineSaleBean = onlineSaleBeanList.get(i);
            TableModel tableMode = new TableModel();

            tableMode.setLeftTitle(onlineSaleBean.getCompanyName());
            tableMode.setXuhao("" + (i + 1));
            BigDecimal bd = null;
            String count;
            switch (getIntent().getStringExtra("title")) {

                case "汇聚按客户端查询":
                    String finaDate;


                    if (mP.size() == 0) {
                        finaDate = "" + beans.getData().get(i).getMonth();
                        tableMode.setText0(beans.getData().get(i).getName());//列0内容
                        tableMode.setText1(beans.getData().get(i).getDbcname() + "");//列1内容
                        tableMode.setText2(beans.getData().get(i).getTabcname() + "");//列2内容
                        if (type == 1) {
                            tableMode.setText3(finaDate.substring(0, 4) + "-" + finaDate.substring(4, finaDate.length()));

                        } else {
                            String date = beans.getData().get(i).getDate() + "";
                            tableMode.setText3(date.substring(0, date.indexOf("T")));//列1内容

                        }

                        bd = new BigDecimal(beans.getData().get(i).getRows_sum());
                        count = bd.toPlainString();

                        tableMode.setText4(count);

                    } else {
                        finaDate = "" + beans.getData().get(mP.get(i)).getMonth();
                        tableMode.setText0(beans.getData().get(mP.get(i)).getName());//列0内容
                        tableMode.setText1(beans.getData().get(mP.get(i)).getDbcname() + "");//列1内容
                        tableMode.setText2(beans.getData().get(mP.get(i)).getTabcname() + "");//列2内容

                        if (type == 1) {
                            tableMode.setText3(finaDate.substring(0, 4) + "-" + finaDate.substring(4, finaDate.length()));

                        } else {
                            String date = beans.getData().get(mP.get(i)).getDate() + "";
                            tableMode.setText3(date.substring(0, date.indexOf("T")));//列1内容

                        }


                        bd = new BigDecimal(beans.getData().get(mP.get(i)).getRows_sum());
                        count = bd.toPlainString();

                        tableMode.setText4(count);


                    }
                    break;
                case "汇聚按资源查询":


                    if (mP.size() == 0) {
                        finaDate = "" + hjZYCXBean.getData().get(i).getMonth();
                        tableMode.setText0(hjZYCXBean.getData().get(i).getDbcname() + "");//列1内容
                        tableMode.setText1(hjZYCXBean.getData().get(i).getTabcname() + "");//列2内容

                        if (type == 1) {
                            tableMode.setText2(finaDate.substring(0, 4) + "-" + finaDate.substring(4, finaDate.length()));

                        } else {
                            String date = hjZYCXBean.getData().get(i).getDate() + "";
                            tableMode.setText2(date.substring(0, date.indexOf("T")));//列1内容

                        }


                        bd = new BigDecimal(hjZYCXBean.getData().get(i).getRows_sum());
                        count = bd.toPlainString();

                        tableMode.setText3(count);

                    } else {
                        finaDate = "" + hjZYCXBean.getData().get(mP.get(i)).getMonth();
                        tableMode.setText0(hjZYCXBean.getData().get(mP.get(i)).getDbcname() + "");//列1内容
                        tableMode.setText1(hjZYCXBean.getData().get(mP.get(i)).getTabcname() + "");//列2内容


                        if (type == 1) {
                            tableMode.setText2(finaDate.substring(0, 4) + "-" + finaDate.substring(4, finaDate.length()));

                        } else {
                            String date = hjZYCXBean.getData().get(mP.get(i)).getDate() + "";
                            tableMode.setText2(date.substring(0, date.indexOf("T")));//列1内容

                        }

                        bd = new BigDecimal(hjZYCXBean.getData().get(mP.get(i)).getRows_sum());
                        count = bd.toPlainString();

                        tableMode.setText3(count);

                    }


                    break;
                case "汇聚按单位查询":


                    if (mP.size() == 0) {
                        finaDate = "" + fwDwBean.getData().get(i).getMonth();
                        tableMode.setText0(fwDwBean.getData().get(i).getDbcname() + "");//列1内容
                        tableMode.setText1(fwDwBean.getData().get(i).getTabcname() + "");//列2内容

                        if (type == 1) {
                            tableMode.setText2(finaDate.substring(0, 4) + "-" + finaDate.substring(4, finaDate.length()));

                        } else {
                            String date = fwDwBean.getData().get(i).getDate() + "";
                            tableMode.setText2(date.substring(0, date.indexOf("T")));//列1内容

                        }


                        bd = new BigDecimal(fwDwBean.getData().get(i).getRows_sum());
                        count = bd.toPlainString();

                        tableMode.setText3(count);


                    } else {
                        finaDate = "" + fwDwBean.getData().get(mP.get(i)).getMonth();
                        tableMode.setText0(fwDwBean.getData().get(mP.get(i)).getDbcname() + "");//列1内容
                        tableMode.setText1(fwDwBean.getData().get(mP.get(i)).getTabcname() + "");//列2内容
                        if (type == 1) {
                            tableMode.setText2(finaDate.substring(0, 4) + "-" + finaDate.substring(4, finaDate.length()));

                        } else {
                            String date = fwDwBean.getData().get(mP.get(i)).getDate() + "";
                            tableMode.setText2(date.substring(0, date.indexOf("T")));//列1内容

                        }
                        bd = new BigDecimal(fwDwBean.getData().get(mP.get(i)).getRows_sum());
                        count = bd.toPlainString();

                        tableMode.setText3(count);


                    }


                    break;
                case "服务按资源查询":


                    if (mP.size() == 0) {
                        finaDate = "" + fwZYCXBean.getData().get(i).getMonth();

                        tableMode.setText0(fwZYCXBean.getData().get(i).getProc_cname() + "");//列1内容
                        tableMode.setText1(fwZYCXBean.getData().get(i).getName() + "");//列2内容

                        if (type == 1) {
                            tableMode.setText2(finaDate.substring(0, 4) + "-" + finaDate.substring(4, finaDate.length()));

                        } else {
                            String date = fwZYCXBean.getData().get(i).getDate() + "";
                            tableMode.setText2(date.substring(0, date.indexOf("T")));//列1内容

                        }


                        bd = new BigDecimal(fwZYCXBean.getData().get(i).getRows_sum());
                        count = bd.toPlainString();

                        tableMode.setText3(count);


                    } else {

                        finaDate = "" + fwZYCXBean.getData().get(mP.get(i)).getMonth();

                        tableMode.setText0(fwZYCXBean.getData().get(mP.get(i)).getProc_cname() + "");//列1内容
                        tableMode.setText1(fwZYCXBean.getData().get(mP.get(i)).getName() + "");//列2内容

                        if (type == 1) {
                            tableMode.setText2(finaDate.substring(0, 4) + "-" + finaDate.substring(4, finaDate.length()));

                        } else {
                            String date = fwZYCXBean.getData().get(mP.get(i)).getDate() + "";
                            tableMode.setText2(date.substring(0, date.indexOf("T")));//列1内容

                        }
                        bd = new BigDecimal(fwZYCXBean.getData().get(mP.get(i)).getRows_sum());
                        count = bd.toPlainString();

                        tableMode.setText3(count);
                    }
                    break;
                case "服务按单位查询":


                    if (mP.size() == 0) {
                        finaDate = "" + fwDwCXBean.getData().get(i).getMonth();

                        tableMode.setText0(fwDwCXBean.getData().get(i).getProc_cname() + "");//列1内容
                        tableMode.setText1(fwDwCXBean.getData().get(i).getDbcname() + "");//列2内容

                        if (type == 1) {
                            tableMode.setText2(finaDate.substring(0, 4) + "-" + finaDate.substring(4, finaDate.length()));

                        } else {
                            String date = fwDwCXBean.getData().get(i).getDate() + "";
                            tableMode.setText2(date.substring(0, date.indexOf("T")));//列1内容

                        }
                        bd = new BigDecimal(fwDwCXBean.getData().get(i).getRows_sum());
                        count = bd.toPlainString();

                        tableMode.setText3(count);


                    } else {

                        finaDate = "" + fwDwCXBean.getData().get(mP.get(i)).getMonth();

                        tableMode.setText0(fwDwCXBean.getData().get(mP.get(i)).getProc_cname() + "");//列1内容
                        tableMode.setText1(fwDwCXBean.getData().get(mP.get(i)).getDbcname() + "");//列2内容

                        if (type == 1) {
                            tableMode.setText2(finaDate.substring(0, 4) + "-" + finaDate.substring(4, finaDate.length()));

                        } else {
                            String date = fwDwCXBean.getData().get(mP.get(i)).getDate() + "";
                            tableMode.setText2(date.substring(0, date.indexOf("T")));//列1内容

                        }
                        bd = new BigDecimal(fwDwCXBean.getData().get(mP.get(i)).getRows_sum());
                        count = bd.toPlainString();

                        tableMode.setText3(count);
                    }
                    break;
            }


//
//                tableMode.setText4(onlineSaleBean.getSaleAllLast() + "");
//                tableMode.setText5(onlineSaleBean.getSaleAllOneNowLast() + "");//

            mDatas.add(tableMode);
        }
        mLeftAdapter.addData(mDatas, false);
        mRightAdapter.addData(mDatas, false);
        customProgressDialog.dismiss();
        isRefresh = true;
        mDatas.clear();

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_query_details;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_explore:
                /**
                 * 查询 多级筛选
                 */
                /*单位。客户端 ;服务、汇聚
                 * */
                type = 1;
                if (getIntent().getStringExtra("title").contains("单位") || getIntent().getStringExtra("title").contains("客户端")) {
                    Intent intent = new Intent(QueryDetailsActivity.this, SearchActivity.class);
                    intent.putExtra("title", getIntent().getStringExtra("title"));
                    intent.putExtra("type", "1");
                    intent.putExtra("numberType", "Int");
                    startActivityForResult(intent, selectCode);
                } else {
                    /*资源弹框*/
                    showDatePicker(1);
                }

                break;
            case R.id.action_me:
                type = 2;
                /**
                 * 查询 多级筛选
                 */

                if (getIntent().getStringExtra("title").contains("单位") || getIntent().getStringExtra("title").contains("客户端")) {
                    Intent intent = new Intent(QueryDetailsActivity.this, SearchActivity.class);
                    intent.putExtra("title", getIntent().getStringExtra("title"));
                    intent.putExtra("type", "2");
                    intent.putExtra("numberType", "date");
                    startActivityForResult(intent, select2Code);
                } else {
                    /*资源弹框*/
                    showDatePicker(2);
                }

                break;
        }
        return super.onOptionsItemSelected(item);

    }

    private void showDatePicker(int type) {

        Calendar c = Calendar.getInstance();
        new DoubleDatePickerDialog(type, QueryDetailsActivity.this, 3, new DoubleDatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear,
                                  int startDayOfMonth, DatePicker endDatePicker, int endYear, int endMonthOfYear,
                                  int endDayOfMonth) {
                String startTime;
                String endTime;
                /**
                 * 月份不足两位补0
                 */

                String startMonth = "" + (startMonthOfYear + 1);
                String endMonth = "" + (endMonthOfYear + 1);
                endMonth = endMonth.length() == 2 ? endMonth : "0" + endMonth;
                startMonth = startMonth.length() == 2 ? startMonth : "0" + startMonth;
//                startTime = "" + startYear + (startMonthOfYear + 1);
//                endTime = "" + endYear + (endMonthOfYear + 1);
                if (1 == type) {
                    startTime = startYear + "" +
                            startMonth;
                    endTime = endYear + "" + endMonth;
                } else {
                    startTime = startYear + "-" +
                            startMonth + "-" + startDayOfMonth;
                    endTime = endYear + "-" + endMonth + "-" + endDayOfMonth;
                }

                niceType = "" + type;
                initNetMonth("date", "" + niceType, null, startTime, endTime);


            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), false).show();
    }

    /*回调*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {//判断是否返回成功
            niceType = data.getStringExtra("type");
            initNetMonth(data.getStringExtra("numberType"), niceType, data.getStringExtra("id"), data.getStringExtra("startTime"), data.getStringExtra("endTime"));

        }
    }

    private void initNetMonth(String numberType, String type, String id, String startTime, String endTime) {
        ArrayList<String> mListUrlPath = new ArrayList<>();
        customProgressDialog = new CustomProgressDialog(QueryDetailsActivity.this, "");
        customProgressDialog.show();
        mListUrlPath.add("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                "<paras>" +
                "<para><name>clientid</name > <sqldbtype>Int</sqldbtype><value>" + id + "</value></para> " +
                "<para><name>ks</name > <sqldbtype>" + numberType + "</sqldbtype><value>" + startTime + "</value></para> " +
                " <para><name>js</name > <sqldbtype>" + numberType + "</sqldbtype><value>" + endTime + "</value></para>" +
                "</paras>");
        mListUrlPath.add("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                "<paras>" +
                "<para><name>dbid</name > <sqldbtype>Int</sqldbtype><value>7</value></para>" +
                "<para><name>tableid</name > <sqldbtype>Int</sqldbtype><value>19</value></para>" +
                "<para><name>ks</name > <sqldbtype>" + numberType + "</sqldbtype><value> " + startTime + "</value></para>" +
                " <para><name>js</name > <sqldbtype>" + numberType + "</sqldbtype><value> " + endTime + "</value></para>" +
                "</paras>");
        mListUrlPath.add("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                "<paras>" +
                "<para><name>deptid</name > <sqldbtype>Int</sqldbtype><value>" + id + "</value></para>" +
                "<para><name>ks</name > <sqldbtype>" + numberType + "</sqldbtype><value>  " + startTime + "</value></para>" +
                " <para><name>js</name > <sqldbtype>" + numberType + "</sqldbtype><value>  " + endTime + "</value></para>" +
                "</paras>");
        mListUrlPath.add("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                "<paras>" +
                "<para><name>dbid</name > <sqldbtype>Int</sqldbtype><value>1</value></para>" +
                "<para><name>request_fwid</name > <sqldbtype>Int</sqldbtype><value>-1</value></para>" +
                "<para><name>ks</name > <sqldbtype>" + numberType + "</sqldbtype><value>  " + startTime + "</value></para>" +
                " <para><name>js</name > <sqldbtype>" + numberType + "</sqldbtype><value> " + endTime + "</value></para>" +
                "</paras>");
        mListUrlPath.add("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                "<paras>" +
                "<para><name>deptid</name > <sqldbtype>Int</sqldbtype><value>" + id + "</value></para>" +
                "<para><name>ks</name > <sqldbtype>" + numberType + "</sqldbtype><value>  " + startTime + "</value></para>" +
                " <para><name>js</name > <sqldbtype>" + numberType + "</sqldbtype><value> " + endTime + "</value></para>" +
                "</paras>");


        new Thread() {
            @Override
            public void run() {
                String result;
                Message message = new Message();
                Gson gson = new Gson();
                try {
                    switch (getIntent().getStringExtra("title")) {
                        case "汇聚按客户端查询":
                            if ("1".equals(type)) {
                                result = postXml(getIntent().getStringExtra("url"), mListUrlPath.get(0));
                            } else {
                                result = postXml(getIntent().getStringExtra("dayUrl"), mListUrlPath.get(0));
                            }
                            beans = gson.fromJson(result, fwKHDBean.class);

                            message.what = 666;
                            mHandler.sendMessage(message);
                            break;
                        case "汇聚按资源查询":
                            if ("1".equals(type)) {
                                result = postXml(getIntent().getStringExtra("url"), mListUrlPath.get(1));
                            } else {
                                result = postXml(getIntent().getStringExtra("dayUrl"), mListUrlPath.get(1));
                            }
                            hjZYCXBean = gson.fromJson(result, hjZYCXBean.class);

                            message.what = 999;
                            mHandler.sendMessage(message);
                            break;
                        case "汇聚按单位查询":
                            if ("1".equals(type)) {
                                result = postXml(getIntent().getStringExtra("url"), mListUrlPath.get(2));
                            } else {
                                result = postXml(getIntent().getStringExtra("dayUrl"), mListUrlPath.get(2));
                            }
                            fwDwBean = gson.fromJson(result, fwDwBean.class);

                            message.what = 000;
                            mHandler.sendMessage(message);
                            break;
                        case "服务按资源查询":
                            if ("1".equals(type)) {
                                result = postXml(getIntent().getStringExtra("url"), mListUrlPath.get(3));
                            } else {
                                result = postXml(getIntent().getStringExtra("dayUrl"), mListUrlPath.get(3));
                            }
                            fwZYCXBean = gson.fromJson(result, fwZYCXBean.class);


                            message.what = 1000;
                            mHandler.sendMessage(message);
                            break;
                        case "服务按单位查询":
                            if ("1".equals(type)) {
                                result = postXml(getIntent().getStringExtra("url"), mListUrlPath.get(4));
                            } else {
                                result = postXml(getIntent().getStringExtra("dayUrl"), mListUrlPath.get(4));
                            }
                            fwDwCXBean = gson.fromJson(result, fwDwCXBean.class);

                            message.what = 6000;
                            mHandler.sendMessage(message);
                            break;
                    }


                } catch (Exception e) {

                }

            }
        }.start();

    }


}
