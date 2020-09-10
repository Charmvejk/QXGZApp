package com.example.holographicplatformapp.activity.cx;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
import com.example.holographicplatformapp.bean.fwZYCXBean;
import com.example.holographicplatformapp.bean.hjZYCXBean;
import com.example.holographicplatformapp.dialog.DoubleDatePickerDialog;
import com.example.holographicplatformapp.scrrow.SyncHorizontalScrollView;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.holographicplatformapp.HttpUrls.postXml;

public class QueryDetailsActivity extends BaseActivity {
    fwKHDBean beans;
    hjZYCXBean hjZYCXBean;
    fwDwBean fwDwBean;
    fwZYCXBean fwZYCXBean;
    fwDwCXBean fwDwCXBean;
    private static int selectCode = 678;
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


    List<OnlineSaleBean> onlineSaleBeanList = new ArrayList<>();
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {

                case 666:
                    onlineSaleBeanList.clear();
                    for (int i = 0; i < beans.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(beans.getData().get(i).getSystemname()));
                    }

                    setDatas(onlineSaleBeanList);

                    break;
                case 999:
                    onlineSaleBeanList.clear();
                    for (int i = 0; i < hjZYCXBean.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(hjZYCXBean.getData().get(i).getName()));
                    }

                    setDatas(onlineSaleBeanList);

                    break;
                case 000:
                    onlineSaleBeanList.clear();
                    for (int i = 0; i < fwDwBean.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(fwDwBean.getData().get(i).getName()));
                    }

                    setDatas(onlineSaleBeanList);

                    break;
                case 1000:
                    onlineSaleBeanList.clear();
                    for (int i = 0; i < fwZYCXBean.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(fwZYCXBean.getData().get(i).getDbcname()));
                    }

                    setDatas(onlineSaleBeanList);

                    break;
                case 6000:
                    onlineSaleBeanList.clear();
                    for (int i = 0; i < fwDwCXBean.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(fwDwCXBean.getData().get(i).getName()));
                    }

                    setDatas(onlineSaleBeanList);

                    break;
            }
        }

        ;
    };


    @Override
    protected void initDatas() {
        initNet();
        mToolbarTb.setTitle(getIntent().getStringExtra("title"));
    }

    private void initNet() {
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select_type2, menu);
        return true;
    }

    @Override
    protected void initView() {
        tv_table_title_left = (TextView) findViewById(R.id.tv_table_title_left);

        leftListView = (ListView) findViewById(R.id.left_container_listview);
        rightListView = (ListView) findViewById(R.id.right_container_listview);
        right_title_container = (LinearLayout) findViewById(R.id.right_title_container);

        View view = getLayoutInflater().inflate(R.layout.table_right_title, right_title_container);


        TextView tv_table_title_0 = view.findViewById(R.id.tv_table_title_0);
        TextView tv_table_title_1 = view.findViewById(R.id.tv_table_title_1);
        TextView tv_table_title_2 = view.findViewById(R.id.tv_table_title_2);
        TextView tv_table_title_3 = view.findViewById(R.id.tv_table_title_3);
        TextView tv_table_title_4 = view.findViewById(R.id.tv_table_title_4);
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
                tv_table_title_left.setText("资源大类");

                mlist.add("服务名称");
                mlist.add("单位名称");
                mlist.add("月份");
                mlist.add("数量");
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
                tv_table_content_left.setText(item.getLeftTitle());
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
//                item.setTextColor(tv_table_content_right_item5, item.getText5());


            }
        };
        leftListView.setAdapter(mLeftAdapter);
        rightListView.setAdapter(mRightAdapter);
    }

    private void setDatas(List<OnlineSaleBean> onlineSaleBeanList) {

        List<TableModel> mDatas = new ArrayList<>();
        for (int i = 0; i < onlineSaleBeanList.size(); i++) {
            OnlineSaleBean onlineSaleBean = onlineSaleBeanList.get(i);
            TableModel tableMode = new TableModel();
            tableMode.setOrgCode(onlineSaleBean.getOrgCode());
            tableMode.setLeftTitle(onlineSaleBean.getCompanyName());
            BigDecimal bd = null;
            String date;
            switch (getIntent().getStringExtra("title")) {
                case "汇聚按客户端查询":
                    tableMode.setText0(beans.getData().get(i).getName());//列0内容
                    tableMode.setText1(beans.getData().get(i).getDbcname() + "");//列1内容
                    tableMode.setText2(beans.getData().get(i).getTabcname() + "");//列2内容
                    tableMode.setText3("" + beans.getData().get(i).getMonth() + "");
                    bd = new BigDecimal(beans.getData().get(i).getRows_sum());
                    date = bd.toPlainString();

                    tableMode.setText4(date);
                    break;
                case "汇聚按资源查询":
                    tableMode.setText0(hjZYCXBean.getData().get(i).getDbcname() + "");//列1内容
                    tableMode.setText1(hjZYCXBean.getData().get(i).getTabcname() + "");//列2内容
                    tableMode.setText2("" + hjZYCXBean.getData().get(i).getMonth() + "");
                    bd = new BigDecimal(hjZYCXBean.getData().get(i).getRows_sum());
                    date = bd.toPlainString();

                    tableMode.setText3(date);
                    break;
                case "汇聚按单位查询":
                    tableMode.setText0(fwDwBean.getData().get(i).getDbcname() + "");//列1内容
                    tableMode.setText1(fwDwBean.getData().get(i).getTabcname() + "");//列2内容
                    tableMode.setText2("" + fwDwBean.getData().get(i).getMonth() + "");
                    bd = new BigDecimal(fwDwBean.getData().get(i).getRows_sum());
                    date = bd.toPlainString();

                    tableMode.setText3(date);
                    break;
                case "服务按资源查询":
                    tableMode.setText0(fwZYCXBean.getData().get(i).getProc_cname() + "");//列1内容
                    tableMode.setText1(fwZYCXBean.getData().get(i).getName() + "");//列2内容
                    tableMode.setText2("" + fwZYCXBean.getData().get(i).getMonth() + "");
                    bd = new BigDecimal(fwZYCXBean.getData().get(i).getRows_sum());
                    date = bd.toPlainString();

                    tableMode.setText3(date);
                    break;
                case "服务按单位查询":
                    tableMode.setText0(fwDwCXBean.getData().get(i).getProc_cname() + "");//列1内容
                    tableMode.setText1(fwDwCXBean.getData().get(i).getDbcname() + "");//列2内容
                    tableMode.setText2("" + fwDwCXBean.getData().get(i).getMonth() + "");
                    bd = new BigDecimal(fwDwCXBean.getData().get(i).getRows_sum());
                    date = bd.toPlainString();

                    tableMode.setText3(date);
                    break;
            }


//
//                tableMode.setText4(onlineSaleBean.getSaleAllLast() + "");
//                tableMode.setText5(onlineSaleBean.getSaleAllOneNowLast() + "");//

            mDatas.add(tableMode);
        }
        mLeftAdapter.addData(mDatas, false);
        mRightAdapter.addData(mDatas, false);

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
                if (getIntent().getStringExtra("title").contains("单位") || getIntent().getStringExtra("title").contains("客户端")) {
                    Intent intent = new Intent(QueryDetailsActivity.this, SearchActivity.class);
                    intent.putExtra("title", getIntent().getStringExtra("title"));
                    startActivityForResult(intent, selectCode);
                }

                break;
            case R.id.action_me:
                /**
                 *展示日期选择框
                 */

                break;
        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {//判断是否返回成功
            initNetMonth(data.getStringExtra("id"), data.getStringExtra("startTime"), data.getStringExtra("endTime"));
        }
    }

    private void initNetMonth(String id, String startTime, String endTime) {
        ArrayList<String> mListUrlPath = new ArrayList<>();
        mListUrlPath.add("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                "<paras>" +
                "<para><name>clientid</name > <sqldbtype>Int</sqldbtype><value>" + id + "</value></para> " +
                "<para><name>ks</name > <sqldbtype>Int</sqldbtype><value>" + startTime + "</value></para> " +
                " <para><name>js</name > <sqldbtype>Int</sqldbtype><value>" + endTime + "</value></para>" +
                "</paras>");
        mListUrlPath.add("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                "<paras>" +
                "<para><name>dbid</name > <sqldbtype>Int</sqldbtype><value>7</value></para>" +
                "<para><name>tableid</name > <sqldbtype>Int</sqldbtype><value>19</value></para>" +
                "<para><name>ks</name > <sqldbtype>Int</sqldbtype><value> " + startTime + "</value></para>" +
                " <para><name>js</name > <sqldbtype>Int</sqldbtype><value> " + endTime + "</value></para>" +
                "</paras>");
        mListUrlPath.add("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                "<paras>" +
                "<para><name>deptid</name > <sqldbtype>Int</sqldbtype><value>"+id+"</value></para>" +
                "<para><name>ks</name > <sqldbtype>Int</sqldbtype><value>  " + startTime + "</value></para>" +
                " <para><name>js</name > <sqldbtype>Int</sqldbtype><value>  " + endTime + "</value></para>" +
                "</paras>");
        mListUrlPath.add("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                "<paras>" +
                "<para><name>dbid</name > <sqldbtype>Int</sqldbtype><value>1</value></para>" +
                "<para><name>request_fwid</name > <sqldbtype>Int</sqldbtype><value>-1</value></para>" +
                "<para><name>ks</name > <sqldbtype>Int</sqldbtype><value>  " + startTime + "</value></para>" +
                " <para><name>js</name > <sqldbtype>Int</sqldbtype><value> " + endTime + "</value></para>" +
                "</paras>");
        mListUrlPath.add("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                "<paras>" +
                "<para><name>deptid</name > <sqldbtype>Int</sqldbtype><value>"+id+"</value></para>" +
                "<para><name>ks</name > <sqldbtype>Int</sqldbtype><value>  " + startTime + "</value></para>" +
                " <para><name>js</name > <sqldbtype>Int</sqldbtype><value> " + endTime + "</value></para>" +
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

    }

}
