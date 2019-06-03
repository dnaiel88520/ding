package com.example.asus.myapplication.model;

public class order {

    private String selname;
    private String buyname;
    private String status;
    private String ordertime;
    private String alreadyorder;
    private  String orderno;
    private  String orderdetail;
    private  String ordertotal;
    private  String ordercontext;
    private  String buycontext;

    public order() {
    }

    public order(String selname, String buyname, String status, String ordertime, String alreadyorder, String orderno, String orderdetail,String ordertotal,String ordercontext,String buycontext) {
        this.selname = selname;
        this.buyname = buyname;
        this.status = status;
        this.ordertime = ordertime;
        this.alreadyorder = alreadyorder;
        this.orderno = orderno;
        this.orderdetail = orderdetail;
        this.ordertotal = ordertotal;
        this.ordercontext=ordercontext;
        this.buycontext=buycontext;
    }

    public String getOrdercontext() {
        return ordercontext;
    }

    public void setOrdercontext(String ordercontext) {
        this.ordercontext = ordercontext;
    }

    public String getBuycontext() {
        return buycontext;
    }

    public void setBuycontext(String buycontext) {
        this.buycontext = buycontext;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getOrdertotal() {
        return ordertotal;
    }

    public void setOrdertotal(String ordertotal) {
        this.ordertotal = ordertotal;
    }

    public String getOrderdetail() {
        return orderdetail;
    }

    public void setOrderdetail(String orderdetail) {
        this.orderdetail = orderdetail;
    }

    public String getSelname() {
        return selname;
    }

    public void setSelname(String selname) {
        this.selname = selname;
    }

    public String getBuyname() {
        return buyname;
    }

    public void setBuyname(String buyname) {
        this.buyname = buyname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getAlreadyorder() {
        return alreadyorder;
    }

    public void setAlreadyorder(String alreadyorder) {
        this.alreadyorder = alreadyorder;
    }

    public String getOrderid() {
        return orderno;
    }

    public void setOrderid(String orderno) {
        this.orderno = orderno;
    }
}
