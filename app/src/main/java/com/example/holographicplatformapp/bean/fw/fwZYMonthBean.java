package com.example.holographicplatformapp.bean.fw;

import java.util.List;

public class fwZYMonthBean {

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
         * month : 201912
         * rows_sum : 296239
         */

        private String dbcname;
        private String proc_cname;
        private String Name;
        private int month;
        private int rows_sum;

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

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getRows_sum() {
            return rows_sum;
        }

        public void setRows_sum(int rows_sum) {
            this.rows_sum = rows_sum;
        }
    }
}
