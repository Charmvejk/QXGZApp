package com.example.holographicplatformapp.bean;

import java.util.List;

public class hjZYFWBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 6
         * dbcname : 滨州旅客购票信息
         */

        private int id;
        private String dbcname;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;

        public String getProc_cname() {
            return proc_cname;
        }

        public void setProc_cname(String proc_cname) {
            this.proc_cname = proc_cname;
        }

        public int getAutoid() {
            return autoid;
        }

        public void setAutoid(int autoid) {
            this.autoid = autoid;
        }

        private String proc_cname;
        private int autoid;
        public String getTablecname() {
            return tablecname;
        }

        public void setTablecname(String tablecname) {
            this.tablecname = tablecname;
        }

        private String tablecname;

        public int getTableid() {
            return tableid;
        }

        public void setTableid(int tableid) {
            this.tableid = tableid;
        }

        private int tableid;
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDbcname() {
            return dbcname;
        }

        public void setDbcname(String dbcname) {
            this.dbcname = dbcname;
        }
    }
}
