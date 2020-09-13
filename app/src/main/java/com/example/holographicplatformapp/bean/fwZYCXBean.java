package com.example.holographicplatformapp.bean;

import java.util.List;

public class fwZYCXBean {

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
         * Name : 山东省公安厅
         * month : 201912
         * dbcname : 采集对象
         * proc_cname : 系统根据数据行版本同步车辆卡口信息
         * rows_sum : 58366
         */
        private String date;
        private String Name;
        private int month;
        private String dbcname;
        private String proc_cname;
        private int rows_sum;

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

        public String getProc_cname() {
            return proc_cname;
        }

        public void setProc_cname(String proc_cname) {
            this.proc_cname = proc_cname;
        }

        public int getRows_sum() {
            return rows_sum;
        }

        public void setRows_sum(int rows_sum) {
            this.rows_sum = rows_sum;
        }
    }
}
