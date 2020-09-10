package com.example.holographicplatformapp.bean;

import java.util.List;

public class MainDatasBean {

    private List<TableBean> Table;
    private List<Table1Bean> Table1;
    private List<Table2Bean> Table2;
    private List<Table3Bean> Table3;
    private List<Table4Bean> Table4;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public List<Table1Bean> getTable1() {
        return Table1;
    }

    public void setTable1(List<Table1Bean> Table1) {
        this.Table1 = Table1;
    }

    public List<Table2Bean> getTable2() {
        return Table2;
    }

    public void setTable2(List<Table2Bean> Table2) {
        this.Table2 = Table2;
    }

    public List<Table3Bean> getTable3() {
        return Table3;
    }

    public void setTable3(List<Table3Bean> Table3) {
        this.Table3 = Table3;
    }

    public List<Table4Bean> getTable4() {
        return Table4;
    }

    public void setTable4(List<Table4Bean> Table4) {
        this.Table4 = Table4;
    }

    public static class TableBean {
        /**
         * rows : 27796114526
         */

        private long rows;

        public long getRows() {
            return rows;
        }

        public void setRows(long rows) {
            this.rows = rows;
        }
    }

    public static class Table1Bean {
        /**
         * rows : 7
         */

        private int rows;

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }
    }

    public static class Table2Bean {
        /**
         * rows : 14
         */

        private int rows;

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }
    }

    public static class Table3Bean {
        /**
         * rows : 6804
         */

        private int rows;

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }
    }

    public static class Table4Bean {
        /**
         * rows : 16775632268
         */

        private long rows;

        public long getRows() {
            return rows;
        }

        public void setRows(long rows) {
            this.rows = rows;
        }
    }
}
