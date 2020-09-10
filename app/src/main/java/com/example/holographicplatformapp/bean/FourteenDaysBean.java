package com.example.holographicplatformapp.bean;

import java.util.List;

public class FourteenDaysBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * groups : 出库量
         * sl : 87301437
         * name : 2020-02-18
         */

        private String groups;
        private int sl;
        private String name;

        public String getGroups() {
            return groups;
        }

        public void setGroups(String groups) {
            this.groups = groups;
        }

        public int getSl() {
            return sl;
        }

        public void setSl(int sl) {
            this.sl = sl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
