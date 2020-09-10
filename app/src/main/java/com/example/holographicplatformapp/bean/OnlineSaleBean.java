package com.example.holographicplatformapp.bean;

/**
 * 实际的数据模型（在多个种表格的常见下，要单独建类似的模型）
 * <p>
 * 特点：每条记录包含行标题与行的所有列内容
 * <p>
 * demo可以直接使用TableModel
 * Created by wsy
 */
public class OnlineSaleBean extends BaseBean {

    private int id;

    private String orgCode;

    private String saleAll;
    private String saleAllOneNow;

    private String onlineSaleOneNow;
    private String retailOnlineSale;
    private String retailOnlineSaleOneNow;
    private String addUserId;
    private String addUser;
    private String areaName;
    private String companyName;
    private String saleAllLast;
    private String saleAllOneNowLast;


    public OnlineSaleBean(String companyName) {
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }


    public String getSaleAll() {
        return saleAll;
    }

    public void setSaleAll(String saleAll) {
        this.saleAll = saleAll;
    }

    public String getSaleAllOneNow() {
        return saleAllOneNow;
    }

    public void setSaleAllOneNow(String saleAllOneNow) {
        this.saleAllOneNow = saleAllOneNow;
    }


    public String getOnlineSaleOneNow() {
        return onlineSaleOneNow;
    }

    public void setOnlineSaleOneNow(String onlineSaleOneNow) {
        this.onlineSaleOneNow = onlineSaleOneNow;
    }

    public String getRetailOnlineSale() {
        return retailOnlineSale;
    }

    public void setRetailOnlineSale(String retailOnlineSale) {
        this.retailOnlineSale = retailOnlineSale;
    }

    public String getRetailOnlineSaleOneNow() {
        return retailOnlineSaleOneNow;
    }

    public void setRetailOnlineSaleOneNow(String retailOnlineSaleOneNow) {
        this.retailOnlineSaleOneNow = retailOnlineSaleOneNow;
    }

    public String getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(String addUserId) {
        this.addUserId = addUserId;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSaleAllLast() {
        return saleAllLast;
    }

    public void setSaleAllLast(String saleAllLast) {
        this.saleAllLast = saleAllLast;
    }

    public String getSaleAllOneNowLast() {
        return saleAllOneNowLast;
    }


}
