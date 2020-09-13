package com.example.holographicplatformapp.bean;

import java.util.List;

public class fwKHDBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        /**
         * clientname : zibo-yisa-oioviae
         * systemname : 淄博以萨汇聚视频/图像采集设备信息
         * Name : 淄博市公安局
         * month : 201912
         * dbcname : 采集对象
         * tabcname : 视频/图像采集设备对象信息表
         * rows_sum : 164770
         */
        private String date;
        private String clientname;
        private String systemname;
        private String Name;
        private int month;
        private String dbcname;
        private String tabcname;
        private double rows_sum;

        public String getClientname() {
            return clientname;
        }

        public void setClientname(String clientname) {
            this.clientname = clientname;
        }

        public String getSystemname() {
            return systemname;
        }

        public void setSystemname(String systemname) {
            this.systemname = systemname;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public String getDbcname() {
            return dbcname;
        }

        public void setDbcname(String dbcname) {
            this.dbcname = dbcname;
        }

        public String getTabcname() {
            return tabcname;
        }

        public void setTabcname(String tabcname) {
            this.tabcname = tabcname;
        }

        public double getRows_sum() {
            return rows_sum;
        }

        public void setRows_sum(int rows_sum) {
            this.rows_sum = rows_sum;
        }
    }
}
