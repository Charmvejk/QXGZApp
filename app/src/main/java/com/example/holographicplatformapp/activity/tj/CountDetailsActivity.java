package com.example.holographicplatformapp.activity.tj;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.holographicplatformapp.R;
import com.example.holographicplatformapp.activity.BaseActivity;
import com.example.holographicplatformapp.adapter.AbsCommonAdapter;
import com.example.holographicplatformapp.adapter.AbsViewHolder;
import com.example.holographicplatformapp.bean.HjDWTitlesBean;
import com.example.holographicplatformapp.bean.HjZyTitlesBean;
import com.example.holographicplatformapp.bean.OnlineSaleBean;
import com.example.holographicplatformapp.bean.TableModel;
import com.example.holographicplatformapp.bean.countTitlesBean;
import com.example.holographicplatformapp.bean.fw.ffwKHDMonthBean;
import com.example.holographicplatformapp.bean.fw.fwDWMonthBean;
import com.example.holographicplatformapp.bean.fw.fwZYMonthBean;
import com.example.holographicplatformapp.bean.fwKHTitlesBean;
import com.example.holographicplatformapp.bean.fwZyTitlesBean;
import com.example.holographicplatformapp.bean.hj.hjDWMonthBean;
import com.example.holographicplatformapp.bean.hj.hjKHDMonthBean;
import com.example.holographicplatformapp.bean.hj.hjZYMonthBean;
import com.example.holographicplatformapp.dialog.DoubleDatePickerDialog;
import com.example.holographicplatformapp.scrrow.SyncHorizontalScrollView;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.holographicplatformapp.HttpUrls.postXml;

public class CountDetailsActivity extends BaseActivity {

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

    private countTitlesBean beans;//表头标题
    private HjZyTitlesBean hjZyTitlesBean;//表头标题
    private HjDWTitlesBean hjDWTitlesBean;//表头标题
    private fwKHTitlesBean fwKHTitlesBean;//表头标题
    private fwZyTitlesBean fwZyTitlesBean;//表头标题
    private hjDWMonthBean hjDWMonthBean;//表头标题
    private ffwKHDMonthBean ffwKHDMonthBean;//表头标题
    private fwZYMonthBean fwZYMonthBean;//表头标题
    private fwDWMonthBean fwDWMonthBean;//表头标题


    private TextView tv_table_title_4;//月份
    private TextView tv_table_title_3;//月份

    private boolean isSelectType = false;


    private hjKHDMonthBean hjKHDMonthBean;//按月
    private hjZYMonthBean hjZYMonthBean;//表头标题


    List<OnlineSaleBean> onlineSaleBeanList = new ArrayList<>();
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {

                case 666:

                    onlineSaleBeanList.clear();
                    for (int i = 0; i < beans.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(beans.getData().get(i).getSystemname()));
                    }
                    if (onlineSaleBeanList.size() != 0) {
                        isSelectType = false;
                    }
                    setDatas(onlineSaleBeanList);

                    break;
                case 777:
                    //显示月份
                    onlineSaleBeanList.clear();


                    for (int i = 0; i < hjKHDMonthBean.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(hjKHDMonthBean.getData().get(i).getSystemname()));
                    }

                    if (onlineSaleBeanList.size() != 0) {
                        isSelectType = true;
                        tv_table_title_4.setVisibility(View.VISIBLE);
                    }
                    setDatas(onlineSaleBeanList);

                    break;
                case 1000:
                    onlineSaleBeanList.clear();
                    for (int i = 0; i < fwKHTitlesBean.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(fwKHTitlesBean.getData().get(i).getSystemname()));
                    }

                    setDatas(onlineSaleBeanList);

                    break;
                case 999:
                    onlineSaleBeanList.clear();
                    for (int i = 0; i < hjZyTitlesBean.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(hjZyTitlesBean.getData().get(i).getName()));
                    }

                    setDatas(onlineSaleBeanList);


                    break;
                case 1999:

                    onlineSaleBeanList.clear();
                    for (int i = 0; i < hjZYMonthBean.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(hjZYMonthBean.getData().get(i).getName()));
                    }

                    if (onlineSaleBeanList.size() != 0) {
                        tv_table_title_3.setVisibility(View.VISIBLE);
                        isSelectType = true;
                    }
                    setDatas(onlineSaleBeanList);


                    break;
                case 1111:
                    onlineSaleBeanList.clear();

                    for (int i = 0; i < fwZyTitlesBean.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(fwZyTitlesBean.getData().get(i).getName()));
                    }

                    setDatas(onlineSaleBeanList);


                    break;
                case 000:
                    onlineSaleBeanList.clear();
                    for (int i = 0; i < hjDWTitlesBean.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(hjDWTitlesBean.getData().get(i).getName()));
                    }

                    setDatas(onlineSaleBeanList);


                    break;
                case 111000:
                    onlineSaleBeanList.clear();
                    for (int i = 0; i < ffwKHDMonthBean.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(ffwKHDMonthBean.getData().get(i).getName()));
                    }
                    if (onlineSaleBeanList.size() != 0) {
                        tv_table_title_4.setVisibility(View.VISIBLE);
                        isSelectType = true;
                    }
                    setDatas(onlineSaleBeanList);


                    break;
                case 9090:
                    onlineSaleBeanList.clear();
                    for (int i = 0; i < fwZYMonthBean.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(fwZYMonthBean.getData().get(i).getName()));
                    }
                    if (onlineSaleBeanList.size() != 0) {
                        tv_table_title_3.setVisibility(View.VISIBLE);
                        isSelectType = true;
                    }
                    setDatas(onlineSaleBeanList);


                    break;
                case 000123:
                    onlineSaleBeanList.clear();

                    for (int i = 0; i < hjDWMonthBean.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(hjDWMonthBean.getData().get(i).getName()));
                    }

                    if (onlineSaleBeanList.size() != 0) {
                        tv_table_title_3.setVisibility(View.VISIBLE);
                        isSelectType = true;
                    }
                    setDatas(onlineSaleBeanList);

                    break;
                case 9988:
                    onlineSaleBeanList.clear();

                    for (int i = 0; i < fwDWMonthBean.getData().size(); i++) {
                        onlineSaleBeanList.add(new OnlineSaleBean(fwDWMonthBean.getData().get(i).getName()));
                    }

                    if (onlineSaleBeanList.size() != 0) {
                        tv_table_title_3.setVisibility(View.VISIBLE);
                        isSelectType = true;
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

        new Thread() {
            @Override
            public void run() {
                Message message = new Message();
                String path = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                        "<paras>" +
                        "</paras>";
                String result = postXml(getIntent().getStringExtra("url"), path);
                Gson gson = new Gson();
                try {
                    switch (getIntent().getStringExtra("title")) {
                        case "汇聚按客户端统计":
                            beans = gson.fromJson(result, countTitlesBean.class);

                            message.what = 666;
                            mHandler.sendMessage(message);
                            break;
                        case "服务按客户端统计":
                            fwKHTitlesBean = gson.fromJson(result, fwKHTitlesBean.class);

                            message.what = 1000;
                            mHandler.sendMessage(message);

                            break;
                        case "汇聚按资源统计":
                            hjZyTitlesBean = gson.fromJson(result, HjZyTitlesBean.class);

                            message.what = 999;
                            mHandler.sendMessage(message);

                            break;
                        case "服务按资源统计":
                            fwZyTitlesBean = gson.fromJson(result, fwZyTitlesBean.class);

                            message.what = 1111;
                            mHandler.sendMessage(message);

                            break;
                        case "服务按单位统计":

                            fwZyTitlesBean = gson.fromJson(result, fwZyTitlesBean.class);

                            message.what = 1111;
                            mHandler.sendMessage(message);

                            break;
                        case "汇聚按单位统计":
                            hjDWTitlesBean = gson.fromJson(result, HjDWTitlesBean.class);

                            message.what = 000;
                            mHandler.sendMessage(message);

                            break;

                    }


                } catch (Exception e) {

                }

            }
        }.start();

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
        tv_table_title_3 = view.findViewById(R.id.tv_table_title_3);
        tv_table_title_4 = view.findViewById(R.id.tv_table_title_4);
        List<String> mlist = new ArrayList<>();
        mlist.clear();
        switch (getIntent().getStringExtra("title")) {
            case "汇聚按客户端统计":
                tv_table_title_left.setText("客户端名称");
                mlist.add("单位名称");
                mlist.add("资源大类");
                mlist.add("资源小类");
                mlist.add("数量");
                refreshUI(mlist, tv_table_title_0, tv_table_title_1, tv_table_title_2, tv_table_title_3, tv_table_title_4);

                break;
            case "服务按客户端统计":
                tv_table_title_left.setText("对接系统业务");
                mlist.add("资源大类");
                mlist.add("单位名称");

                mlist.add("服务名称");
                mlist.add("数量");
                refreshUI(mlist, tv_table_title_0, tv_table_title_1, tv_table_title_2, tv_table_title_3, tv_table_title_4);

                break;
            case "汇聚按资源统计":
                tv_table_title_left.setText("单位名称");

                mlist.add("资源大类");
                mlist.add("资源小类");
                mlist.add("数量");
                refreshUI(mlist, tv_table_title_0, tv_table_title_1, tv_table_title_2, tv_table_title_3, tv_table_title_4);

                break;
            case "服务按资源统计":
                tv_table_title_left.setText("单位名称");

                mlist.add("服务名称");
                mlist.add("资源大类");
                mlist.add("数量");
                refreshUI(mlist, tv_table_title_0, tv_table_title_1, tv_table_title_2, tv_table_title_3, tv_table_title_4);

                break;
            case "服务按单位统计":
                tv_table_title_left.setText("单位名称");

                mlist.add("服务名称");
                mlist.add("资源大类");
                mlist.add("数量");
                refreshUI(mlist, tv_table_title_0, tv_table_title_1, tv_table_title_2, tv_table_title_3, tv_table_title_4);

                break;
            case "汇聚按单位统计":
                tv_table_title_left.setText("单位名称");

                mlist.add("资源大类");
                mlist.add("资源小类");
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
        if (mlist.size() == 4) {
            tv_table_title_0.setText(mlist.get(0));
            tv_table_title_1.setText(mlist.get(1));
            tv_table_title_2.setText(mlist.get(2));
            tv_table_title_3.setText(mlist.get(3));
            tv_table_title_4.setVisibility(View.GONE);
        } else if (mlist.size() == 3) {
            tv_table_title_0.setText(mlist.get(0));
            tv_table_title_1.setText(mlist.get(1));
            tv_table_title_2.setText(mlist.get(2));
            tv_table_title_2.setGravity(Gravity.CENTER);
            tv_table_title_3.setVisibility(View.GONE);
            tv_table_title_4.setVisibility(View.GONE);
        }


    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_count_dteails;
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
                    case "汇聚按客户端统计":
                        if (!isSelectType) {
                            tv_table_content_right_item4.setVisibility(View.GONE);
                        } else {
                            tv_table_content_right_item4.setVisibility(View.VISIBLE);
                        }
                        break;
                    case "服务按客户端统计":
                        if (!isSelectType) {
                            tv_table_content_right_item4.setVisibility(View.GONE);
                        } else {
                            tv_table_content_right_item4.setVisibility(View.VISIBLE);
                        }

                        break;
                    case "汇聚按资源统计":
                        if (!isSelectType) {
                            tv_table_content_right_item3.setVisibility(View.GONE);
                        } else {
                            tv_table_content_right_item3.setVisibility(View.VISIBLE);
                        }

                        tv_table_content_right_item4.setVisibility(View.GONE);
                        break;
                    case "服务按资源统计":
                        if (!isSelectType) {
                            tv_table_content_right_item3.setVisibility(View.GONE);
                        } else {
                            tv_table_content_right_item3.setVisibility(View.VISIBLE);
                        }

                        tv_table_content_right_item4.setVisibility(View.GONE);

                        break;
                    case "服务按单位统计":
                        if (!isSelectType) {
                            tv_table_content_right_item3.setVisibility(View.GONE);
                        } else {
                            tv_table_content_right_item3.setVisibility(View.VISIBLE);
                        }
                        tv_table_content_right_item4.setVisibility(View.GONE);
                        break;
                    case "汇聚按单位统计":
                        if (!isSelectType) {
                            tv_table_content_right_item3.setVisibility(View.GONE);
                        } else {
                            tv_table_content_right_item3.setVisibility(View.VISIBLE);
                        }

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select_type, menu);
        return true;
    }

    private void setDatas(List<OnlineSaleBean> onlineSaleBeanList) {
        List<TableModel> mDatas = new ArrayList<>();
        for (int i = 0; i < onlineSaleBeanList.size(); i++) {
            OnlineSaleBean onlineSaleBean = onlineSaleBeanList.get(i);
            TableModel tableMode = new TableModel();
            tableMode.setOrgCode(onlineSaleBean.getOrgCode());
            tableMode.setLeftTitle(onlineSaleBean.getCompanyName());
            BigDecimal bd = null;
            String count;
            switch (getIntent().getStringExtra("title")) {
                case "汇聚按客户端统计":
                    if (isSelectType) {
                        tableMode.setText0(hjKHDMonthBean.getData().get(i).getName());//列0内容
                        tableMode.setText1(hjKHDMonthBean.getData().get(i).getDbcname() + "");//列1内容
                        tableMode.setText2(hjKHDMonthBean.getData().get(i).getTabcname() + "");//列2内容
                        bd = new BigDecimal(hjKHDMonthBean.getData().get(i).getRows_sum());
                        count = bd.toPlainString();
                        tableMode.setText3(count);
                        tableMode.setText4("" + hjKHDMonthBean.getData().get(i).getMonth() + "");//列2内容
                    } else {
                        tableMode.setText0(beans.getData().get(i).getName());//列0内容
                        tableMode.setText1(beans.getData().get(i).getDbcname() + "");//列1内容
                        tableMode.setText2(beans.getData().get(i).getTabcname() + "");//列2内容
                        bd = new BigDecimal(beans.getData().get(i).getRows_sum());
                        count = bd.toPlainString();
                        tableMode.setText3(count);
                    }
                    break;
                case "服务按客户端统计":
                    if (isSelectType) {
                        tableMode.setText0(ffwKHDMonthBean.getData().get(i).getDbcname());//列0内容
                        tableMode.setText1(ffwKHDMonthBean.getData().get(i).getName() + "");//列1内容
                        tableMode.setText2(ffwKHDMonthBean.getData().get(i).getProc_cname() + "");//列2内容
                        bd = new BigDecimal(ffwKHDMonthBean.getData().get(i).getRows_sum());
                        count = bd.toPlainString();
                        tableMode.setText3(count);
                        tableMode.setText4("" + ffwKHDMonthBean.getData().get(i).getMonth());
                    } else {
                        tableMode.setText0(fwKHTitlesBean.getData().get(i).getDbcname());//列0内容
                        tableMode.setText1(fwKHTitlesBean.getData().get(i).getName() + "");//列1内容
                        tableMode.setText2(fwKHTitlesBean.getData().get(i).getProc_cname() + "");//列2内容
                        bd = new BigDecimal(fwKHTitlesBean.getData().get(i).getRows_sum());
                        count = bd.toPlainString();
                        tableMode.setText3(count);
                    }
                    break;
                case "汇聚按资源统计":
                    if (isSelectType) {
                        tableMode.setText0(hjZYMonthBean.getData().get(i).getDbcname());//列0内容
                        tableMode.setText1(hjZYMonthBean.getData().get(i).getTabcname() + "");//列1内容
                        bd = new BigDecimal(hjZYMonthBean.getData().get(i).getRows_sum());
                        count = bd.toPlainString();
                        tableMode.setText2(count);
                        tableMode.setText3("" + hjZYMonthBean.getData().get(i).getMonth());
                    } else {
                        tableMode.setText0(hjZyTitlesBean.getData().get(i).getDbcname());//列0内容
                        tableMode.setText1(hjZyTitlesBean.getData().get(i).getTabcname() + "");//列1内容
                        bd = new BigDecimal(hjZyTitlesBean.getData().get(i).getRows_sum());
                        count = bd.toPlainString();
                        tableMode.setText2(count);
                    }
                    break;
                case "服务按资源统计":

                    if (isSelectType) {
                        tableMode.setText0(fwZYMonthBean.getData().get(i).getProc_cname());//列0内容
                        tableMode.setText1(fwZYMonthBean.getData().get(i).getDbcname() + "");//列1内容
                        bd = new BigDecimal(fwZYMonthBean.getData().get(i).getRows_sum());
                        count = bd.toPlainString();
                        tableMode.setText2(count);
                        tableMode.setText3(""+fwZYMonthBean.getData().get(i).getMonth());
                    } else {
                        tableMode.setText0(fwZyTitlesBean.getData().get(i).getProc_cname());//列0内容
                        tableMode.setText1(fwZyTitlesBean.getData().get(i).getDbcname() + "");//列1内容
                        bd = new BigDecimal(fwZyTitlesBean.getData().get(i).getRows_sum());
                        count = bd.toPlainString();
                        tableMode.setText2(count);
                    }
                    break;
                case "服务按单位统计":

                    if (isSelectType) {
                        tableMode.setText0(fwDWMonthBean.getData().get(i).getProc_cname());//列0内容
                        tableMode.setText1(fwDWMonthBean.getData().get(i).getDbcname() + "");//列1内容
                        bd = new BigDecimal(fwDWMonthBean.getData().get(i).getRows_sum());
                        count = bd.toPlainString();
                        tableMode.setText2(count);
                        tableMode.setText3(""+fwDWMonthBean.getData().get(i).getMonth());

                    } else {
                        tableMode.setText0(fwZyTitlesBean.getData().get(i).getProc_cname());//列0内容
                        tableMode.setText1(fwZyTitlesBean.getData().get(i).getDbcname() + "");//列1内容
                        bd = new BigDecimal(fwZyTitlesBean.getData().get(i).getRows_sum());
                        count = bd.toPlainString();
                        tableMode.setText2(count);
                    }
                    break;
                case "汇聚按单位统计":
                    if (isSelectType) {
                        tableMode.setText0(hjDWMonthBean.getData().get(i).getDbcname());//列0内容
                        tableMode.setText1(hjDWMonthBean.getData().get(i).getTabcname() + "");//列1内容
                        bd = new BigDecimal(hjDWMonthBean.getData().get(i).getRows_sum());
                        count = bd.toPlainString();
                        tableMode.setText2(count);
                        tableMode.setText3("" + hjDWMonthBean.getData().get(i).getMonth());
                    } else {
                        tableMode.setText0(hjDWTitlesBean.getData().get(i).getDbcname());//列0内容
                        tableMode.setText1(hjDWTitlesBean.getData().get(i).getTabcname() + "");//列1内容
                        bd = new BigDecimal(hjDWTitlesBean.getData().get(i).getRows_sum());
                        count = bd.toPlainString();
                        tableMode.setText2(count);
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

        mDatas.clear();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_home:
                initNet();
                break;
            case R.id.action_explore:
                /**
                 *展示日期选择框
                 */
                showDatePicker();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    private void showDatePicker() {
        Calendar c = Calendar.getInstance();
        new DoubleDatePickerDialog(CountDetailsActivity.this, 3, new DoubleDatePickerDialog.OnDateSetListener() {

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
                endMonth = endMonth.length() > 2 ? endMonth : "0" + endMonth;
                startMonth = startMonth.length() > 2 ? startMonth : "0" + startMonth;
//                startTime = "" + startYear + (startMonthOfYear + 1);
//                endTime = "" + endYear + (endMonthOfYear + 1);
                startTime = startYear + "" +
                        startMonth;
                endTime = endYear + "" + endMonth;
                initNetMonth(startTime, endTime);


            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), false).show();
    }

    private void initNetMonth(String startTime, String endTime) {

        new Thread() {
            @Override
            public void run() {
                Message message = new Message();
                String path = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                        "<paras>" +
                        "<para><name>ks</name > <sqldbtype>Int</sqldbtype><value>" + startTime + "</value></para>" +
                        " <para><name>js</name > <sqldbtype>Int</sqldbtype><value>" + endTime + "</value></para>" +
                        "</paras>";
                String result = postXml(getIntent().getStringExtra("dayUrl"), path);
                Gson gson = new Gson();
                try {
                    switch (getIntent().getStringExtra("title")) {
                        case "汇聚按客户端统计":
                            hjKHDMonthBean = gson.fromJson(result, hjKHDMonthBean.class);

                            message.what = 777;
                            mHandler.sendMessage(message);
                            break;
                        case "服务按客户端统计":
                            ffwKHDMonthBean = gson.fromJson(result, ffwKHDMonthBean.class);

                            message.what = 111000;
                            mHandler.sendMessage(message);

                            break;
                        case "汇聚按资源统计":
                            hjZYMonthBean = gson.fromJson(result, hjZYMonthBean.class);

                            message.what = 1999;
                            mHandler.sendMessage(message);

                            break;
                        case "服务按资源统计":
                            fwZYMonthBean = gson.fromJson(result, fwZYMonthBean.class);

                            message.what = 9090;
                            mHandler.sendMessage(message);

                            break;
                        case "服务按单位统计":
                            fwDWMonthBean = gson.fromJson(result, fwDWMonthBean.class);

                            message.what = 9988;
                            mHandler.sendMessage(message);

                            break;
                        case "汇聚按单位统计":
                            hjDWMonthBean = gson.fromJson(result, hjDWMonthBean.class);

                            message.what = 000123;
                            mHandler.sendMessage(message);

                            break;

                    }


                } catch (Exception e) {

                }

            }
        }.start();

    }
}
