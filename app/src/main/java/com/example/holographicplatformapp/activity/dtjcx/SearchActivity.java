package com.example.holographicplatformapp.activity.dtjcx;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.holographicplatformapp.R;
import com.example.holographicplatformapp.activity.BaseActivity;
import com.example.holographicplatformapp.bean.DWMCDatasBean;
import com.example.holographicplatformapp.bean.FWDWMCDatasBean;
import com.example.holographicplatformapp.bean.FWKHDMCDatasBean;
import com.example.holographicplatformapp.bean.KHDMCDatasBean;
import com.example.holographicplatformapp.dialog.DoubleDatePickerDialog;
import com.github.promeg.pinyinhelper.Pinyin;
import com.github.promeg.pinyinhelper.PinyinMapDict;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.holographicplatformapp.Constants.ydd_cx_dwmc;
import static com.example.holographicplatformapp.Constants.ydd_cx_fwkhd;
import static com.example.holographicplatformapp.Constants.ydd_cx_hjdw;
import static com.example.holographicplatformapp.Constants.ydd_cx_zydl;
import static com.example.holographicplatformapp.HttpUrls.postXml;

/**
 * 多条件查询
 * wsy
 */

public class SearchActivity extends BaseActivity {
    private RecyclerView mRcSearch;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;
    private LinearLayoutManager manager;
    private List<SortModel> SourceDateList;
    private List<String> mListTitTle;
    private List<String> mListTitTleId;
    private KHDMCDatasBean beans;
    private DWMCDatasBean dwmcDatasBean;
    private FWKHDMCDatasBean fwkhdmcDatasBean;
    private FWDWMCDatasBean fwdwmcDatasBean;
    /**
     * 根据拼音来排列RecyclerView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 666:
                    mListTitTle = new ArrayList<>();
                    mListTitTleId = new ArrayList<>();
                    for (int i = 0; i < beans.getData().size(); i++) {
                        mListTitTle.add(beans.getData().get(i).getSystemname());
                        mListTitTleId.add("" + beans.getData().get(i).getClientid());
                    }
                    byDatats(mListTitTle, mListTitTleId);
                    break;
                case 999:
                    mListTitTle = new ArrayList<>();
                    mListTitTleId = new ArrayList<>();
                    for (int i = 0; i < dwmcDatasBean.getData().size(); i++) {
                        mListTitTle.add(dwmcDatasBean.getData().get(i).getName());
                        mListTitTleId.add("" + dwmcDatasBean.getData().get(i).getID());
                    }
                    byDatats(mListTitTle, mListTitTleId);
                    break;
                case 1999:
                    mListTitTle = new ArrayList<>();
                    mListTitTleId = new ArrayList<>();
                    for (int i = 0; i < fwkhdmcDatasBean.getData().size(); i++) {
                        mListTitTle.add(fwkhdmcDatasBean.getData().get(i).getProc_cname());
                        mListTitTleId.add("" + fwkhdmcDatasBean.getData().get(i).getAutoid());
                    }
                    byDatats(mListTitTle, mListTitTleId);
                    break;
                case 2999:
                    mListTitTle = new ArrayList<>();
                    mListTitTleId = new ArrayList<>();
                    for (int i = 0; i < fwdwmcDatasBean.getData().size(); i++) {
                        mListTitTle.add(fwdwmcDatasBean.getData().get(i).getName());
                        mListTitTleId.add("" + fwdwmcDatasBean.getData().get(i).getID());
                    }
                    byDatats(mListTitTle, mListTitTleId);
                    break;
            }
        }

        ;
    };

    @Override
    protected void initDatas() {
        mToolbarTb.setTitle("多级筛选");
        initNet();

    }

    private void byDatats(List<String> mListTitTle, List<String> mListTitTleId) {
        SourceDateList = filledData(mListTitTle, mListTitTleId);

        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        //RecyclerView社置manager
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRcSearch.setLayoutManager(manager);
        adapter = new SortAdapter(this, SourceDateList);
        mRcSearch.setAdapter(adapter);
        //item点击事件
        adapter.setOnItemClickListener(new SortAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showDatePicker(((SortModel) adapter.getItem(position)).getId());/**/
//                Toast.makeText(SearchActivity.this, ((SortModel)adapter.getItem(position)).getName(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showDatePicker(String name) {
        Calendar c = Calendar.getInstance();
        new DoubleDatePickerDialog(Integer.parseInt(getIntent().getStringExtra("type")), SearchActivity.this, 3, new DoubleDatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear,
                                  int startDayOfMonth, DatePicker endDatePicker, int endYear, int endMonthOfYear,
                                  int endDayOfMonth) {
                String startTime = null;
                String endTime = null;
                /**
                 * 月份不足两位补0
                 */

                String startMonth = "" + (startMonthOfYear + 1);
                String endMonth = "" + (endMonthOfYear + 1);
                endMonth = endMonth.length() == 2 ? endMonth : "0" + endMonth;
                startMonth = startMonth.length() == 2 ? startMonth : "0" + startMonth;
                if ("1".equals(getIntent().getStringExtra("type"))) {
                    startTime = startYear + "" +
                            startMonth;
                    endTime = endYear + "" + endMonth;
                } else {
                    startTime = startYear + "-" +
                            startMonth + "-" + startDayOfMonth;
                    endTime = endYear + "-" + endMonth + "-" + endDayOfMonth;
                }


                Intent intent = new Intent();
                intent.putExtra("id", name);
                intent.putExtra("type", getIntent().getStringExtra("type"));
                intent.putExtra("startTime", startTime);
                intent.putExtra("endTime", endTime);
                intent.putExtra("numberType", getIntent().getStringExtra("numberType"));
                setResult(RESULT_OK, intent);
                finish();


            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), false).show();
    }


    private void initNet() {
        List<String> mList = new ArrayList<>();

        new Thread() {
            @Override
            public void run() {
                try {
                    Gson gson = new Gson();
                    Message message = new Message();
                    String url = null;
                    String result = null;
                    String path = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                            "<paras>" +
                            "</paras>";
                    switch (getIntent().getStringExtra("title")) {
                        case "汇聚按客户端查询":
                            url = ydd_cx_zydl;
                            result = postXml(url, path);
                            beans = gson.fromJson(result, KHDMCDatasBean.class);
                            message.what = 666;
                            mHandler.sendMessage(message);
                            break;
                        case "汇聚按单位查询":
                            url = ydd_cx_hjdw;
                            result = postXml(url, path);
                            dwmcDatasBean = gson.fromJson(result, DWMCDatasBean.class);
                            message.what = 999;
                            mHandler.sendMessage(message);
                            break;
                        case "服务按客户端查询":
                            url = ydd_cx_fwkhd;
                            result = postXml(url, path);
                            fwkhdmcDatasBean = gson.fromJson(result, FWKHDMCDatasBean.class);
                            message.what = 1999;
                            mHandler.sendMessage(message);

                            break;
                        case "服务按单位查询":
                            url = ydd_cx_dwmc;
                            result = postXml(url, path);
                            fwdwmcDatasBean = gson.fromJson(result, FWDWMCDatasBean.class);
                            message.what = 2999;
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
        mRcSearch = (RecyclerView) findViewById(R.id.recyclerView);

        pinyinComparator = new PinyinComparator();
        sideBar = (SideBar) findViewById(R.id.sideBar);
        dialog = (TextView) findViewById(R.id.dialog);
        sideBar.setTextView(dialog);

        //设置右侧SideBar触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    manager.scrollToPositionWithOffset(position, 0);
                }

            }
        });


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
                filterData(s);
                return false;
            }
        });
        return true;

    }

    /**
     * 为RecyclerView填充数据
     *
     * @param date
     * @return
     */
    private List<SortModel> filledData(List<String> date, List<String> ID) {
        List<SortModel> mSortList = new ArrayList<>();

        for (int i = 0; i < date.size(); i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date.get(i));
            sortModel.setId(ID.get(i));

            // 添加自定义词典
            Pinyin.init(Pinyin.newConfig()
                    .with(new PinyinMapDict() {
                        @Override
                        public Map<String, String[]> mapping() {
                            HashMap<String, String[]> map = new HashMap<String, String[]>();
                            map.put("长", new String[]{"CHANG"});
                            return map;
                        }

                    }));
            //汉字转换成拼音
            String pinyin = Pinyin.toPinyin(date.get(i), "/");
            String sortString = pinyin.substring(0, 1).toUpperCase();
            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setLetters(sortString.toUpperCase());
            } else {
                sortModel.setLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }


    /**
     * 根据输入框中的值来过滤数据并更新RecyclerView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : SourceDateList) {
                String name = sortModel.getName();
                /**
                 * 后期首字母
                 */
                if (name.indexOf(filterStr.toString()) != -1 ||
                        Pinyin.toPinyin(name, "/").startsWith(filterStr.toString())
                ) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateList(filterDateList);
    }
}
