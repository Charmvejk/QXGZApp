package com.example.holographicplatformapp.bean;

import java.util.List;

public class fwKHTitlesBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uname : gat-fangz-vehiclekk
         * systemname : 山东公安动态感知一张图
         * Name : 山东省公安厅
         * dbcname : 采集对象
         * proc_cname : 系统根据数据行版本同步车辆卡口信息
         * rows_sum : 960272
         */

        private String uname;
        private String systemname;
        private String Name;
        private String dbcname;
        private String proc_cname;
        private double rows_sum;

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
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

        public double getRows_sum() {
            return rows_sum;
        }

        public void setRows_sum(int rows_sum) {
            this.rows_sum = rows_sum;
        }
    }
}
