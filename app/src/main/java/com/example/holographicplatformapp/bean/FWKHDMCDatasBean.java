package com.example.holographicplatformapp.bean;

import java.util.List;

public class FWKHDMCDatasBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * autoid : 1
         * proc_cname : 淄博市公安局根据主键查询
         */

        private int autoid;
        private String proc_cname;

        public int getAutoid() {
            return autoid;
        }

        public void setAutoid(int autoid) {
            this.autoid = autoid;
        }

        public String getProc_cname() {
            return proc_cname;
        }

        public void setProc_cname(String proc_cname) {
            this.proc_cname = proc_cname;
        }
    }
}
