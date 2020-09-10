package com.example.holographicplatformapp.bean;

import java.util.List;

public class KHDMCDatasBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * clientid : 10000001
         * systemname : 淄博以萨汇聚视频/图像采集设备信息
         */

        private int clientid;
        private String systemname;

        public int getClientid() {
            return clientid;
        }

        public void setClientid(int clientid) {
            this.clientid = clientid;
        }

        public String getSystemname() {
            return systemname;
        }

        public void setSystemname(String systemname) {
            this.systemname = systemname;
        }
    }
}
