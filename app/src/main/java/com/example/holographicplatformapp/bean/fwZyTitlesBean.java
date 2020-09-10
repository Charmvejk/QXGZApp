package com.example.holographicplatformapp.bean;

import java.util.List;

public class fwZyTitlesBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * dbcname : 采集对象
         * proc_cname : 系统根据数据行版本同步人脸卡口信息
         * Name : 淄博市公安局
         * rows_sum : 657951
         */

        private String dbcname;
        private String proc_cname;
        private String Name;
        private double rows_sum;

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

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public double getRows_sum() {
            return rows_sum;
        }

        public void setRows_sum(int rows_sum) {
            this.rows_sum = rows_sum;
        }
    }
}
