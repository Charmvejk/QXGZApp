package com.example.holographicplatformapp.fragment;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.holographicplatformapp.R;
import com.example.holographicplatformapp.activity.BaseFragment;
import com.example.holographicplatformapp.activity.linecharts.LineChartManager;

import com.example.holographicplatformapp.bean.FourteenDaysBean;
import com.example.holographicplatformapp.bean.MainDatasBean;
import com.example.holographicplatformapp.dialog.CustomProgressDialog;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.holographicplatformapp.Constants.db_15day_tj;
import static com.example.holographicplatformapp.Constants.tj_sy5;
import static com.example.holographicplatformapp.HttpUrls.postXml;
import static com.example.holographicplatformapp.utils.NumberUtils.amountConversion;

/*
 * 首页
 * */
public class MainFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private MainDatasBean beans;
    private FourteenDaysBean fourteenDaysBean;
    //todo 双曲线
    private LineChart lineChart1, lineChart2;
    private LineData lineData;
    //14天曲线数据
    private List<Integer> mDatas1;
    private List<Integer> mDatas2;
    private List<String> mListDate;
    private ImageView ivHead;
    private   CustomProgressDialog customProgressDialog;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 666:
                    initRecycleView(beans);

                    break;
                case 999:
                    mDatas1 = new ArrayList<>();
                    mDatas2 = new ArrayList<>();
                    mListDate = new ArrayList<>();
                    for (int i = 0; i < fourteenDaysBean.getData().size(); i++) {
                        if ("出库量".equals(fourteenDaysBean.getData().get(i).getGroups())) {
                            mDatas1.add(fourteenDaysBean.getData().get(i).getSl());

                        } else {
                            mDatas2.add(fourteenDaysBean.getData().get(i).getSl());
                        }
                        String date = fourteenDaysBean.getData().get(i).getName();
                        if (i % 2 == 0) {
                            if (i == 28) {
                                mListDate.add(" " + date.substring(5, date.length()) + "_");
                            } else {
                                mListDate.add(date.substring(5, date.length()));
                            }
                        }

                    }

                    initCharts(mDatas1, mDatas2, mListDate);

                    customProgressDialog.dismiss();

                    break;
            }
        }

        ;
    };

    public static MainFragment newInstance() {
        return new MainFragment();
    }


    @Override
    protected void setUpView() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop(); //设置“加载失败”状态时显示的图片
        Glide.with(getActivity()).load(R.mipmap.icon_top).apply(requestOptions).into(ivHead);

        customProgressDialog = new CustomProgressDialog(getActivity(), "");
        customProgressDialog.show();
    }

    @Override
    protected void setUpData() {
        initNet();

    }

    private void initCharts(List<Integer> mDatas1, List<Integer> mDatas2, List<String> mListDate) {
        //设置图表的描述
        lineChart2.setDescription("");
        //设置x轴的数据
        int numX = 24;
        //设置y轴的数据


        int[] datas1 = new int[0];//数据1
        int[] datas2 = new int[0];//数据2

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            datas1 = mDatas1.stream().mapToInt(Integer::valueOf).toArray();
            datas2 = mDatas2.stream().mapToInt(Integer::valueOf).toArray();
        }
        //数据
        //设置折线的名称
        LineChartManager.setLineName("出库量");
        //设置第二条折线y轴的数据
        LineChartManager.setLineName1("入库量");
        //创建两条折线的图表

        lineData = LineChartManager.initDoubleLineChart(getActivity(), lineChart1, numX, datas1, datas2, mListDate);
        LineChartManager.initDataStyle(lineChart2, lineData, getActivity());
    }


    private void initNet() {

        new Thread() {
            @Override
            public void run() {
                String path = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                        "<paras>" +
                        "</paras>";
                String result = postXml(tj_sy5, path);
                Gson gson = new Gson();
                try {
                    beans = gson.fromJson(result, MainDatasBean.class);
                    Message message = new Message();
                    message.what = 666;
                    mHandler.sendMessage(message);
                } catch (Exception e) {

                }

            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                String path = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>" +
                        "<paras>" +
                        "</paras>";
                String result = postXml(db_15day_tj, path);
                try {
                    Gson gson = new Gson();
                    fourteenDaysBean = gson.fromJson(result, FourteenDaysBean.class);
                    Message message = new Message();
                    message.what = 999;
                    mHandler.sendMessage(message);
                } catch (Exception e) {

                }

            }
        }.start();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.main_fragment;
    }

    @Override
    protected void init(View mContentView) {
        recyclerView = (RecyclerView) mContentView.findViewById(R.id.recyclerView);
        lineChart2 = (LineChart) mContentView.findViewById(R.id.line_chart);
        ivHead = mContentView.findViewById(R.id.iv_head);
    }

    private void initRecycleView(MainDatasBean beans) {

        LinearLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
//设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

//设置Adapter
        recyclerView.setAdapter(new NormalAdapter(beans));
        //设置分隔线

    }

    // ① 创建Adapter
    public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.VH> {
        //② 创建ViewHolder
        MainDatasBean mDatas;

        public class VH extends RecyclerView.ViewHolder {
            private TextView tv_hj_count;
            private TextView tv_hj_title;
            private CardView cardView;
            private ImageView ivMain;

            /**
             * 需要改变颜色的text
             */


            public VH(View v) {
                super(v);
                tv_hj_count = (TextView) v.findViewById(R.id.tv_hj_count);
                tv_hj_title = (TextView) v.findViewById(R.id.tv_hj_title);
                ivMain = v.findViewById(R.id.iv_main);
                cardView = v.findViewById(R.id.cv_content);

            }

        }

        public NormalAdapter(MainDatasBean beans) {
            mDatas = beans;
        }

        //③ 在Adapter中实现3个方法
        @Override
        public void onBindViewHolder(NormalAdapter.VH holder, int position) {
            switch (position) {
                case 0:
                    holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.light_red));
                    holder.tv_hj_title.setText("汇聚总量");
                    holder.ivMain.setImageDrawable(getResources().getDrawable(R.mipmap.icon_sj));
                    holder.tv_hj_count.setText("" + amountConversion(mDatas.getTable().get(0).getRows(), "double"));
                    break;
                case 1:
                    holder.ivMain.setImageDrawable(getResources().getDrawable(R.mipmap.icon_fw));

                    holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.light_green));
                    holder.tv_hj_count.setText("" + amountConversion(mDatas.getTable4().get(0).getRows(), "double"));
                    holder.tv_hj_title.setText("服务总量");
                    break;
                case 2:
                    holder.ivMain.setImageDrawable(getResources().getDrawable(R.mipmap.icon_zydl));

                    holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
                    holder.tv_hj_count.setText("" + amountConversion(mDatas.getTable1().get(0).getRows(), "double"));
                    holder.tv_hj_title.setText("资源大类");
                    break;
                case 3:
                    holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.deep_blue));
                    holder.ivMain.setImageDrawable(getResources().getDrawable(R.mipmap.icon_sjx));
                    holder.tv_hj_count.setText("" + amountConversion(mDatas.getTable3().get(0).getRows(), "double"));

                    holder.tv_hj_title.setText("数据项");
                    break;
            }

        }


        @Override
        public int getItemCount() {
            return 4;
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            //LayoutInflater.from指定写法
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_statistics, parent, false);
            return new VH(v);
        }
    }
}
