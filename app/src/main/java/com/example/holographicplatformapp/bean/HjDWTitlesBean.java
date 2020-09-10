package com.example.holographicplatformapp.bean;

import java.util.List;

public class HjDWTitlesBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Name : 临沂市公安局
         * dbcname : 人脸识别
         * tabcname : 人脸小图属性信息表
         * rows_sum : 116880804
         */

        private String Name;
        private String dbcname;
        private String tabcname;
        private double rows_sum;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
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
