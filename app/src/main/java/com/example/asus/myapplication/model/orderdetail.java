package com.example.asus.myapplication.model;

public class orderdetail {
private  String orderid;
private  String orderdetailid;
private  String quantity;
private  String ice;
private  String sugar;
private  String sellname;
private  String price;
private  String proname;

    public orderdetail() {
    }

    public orderdetail(String orderid, String orderdetailid, String quantity, String ice, String sugar, String sellname, String price, String proname) {
        this.orderid = orderid;
        this.orderdetailid = orderdetailid;
        this.quantity = quantity;
        this.ice = ice;
        this.sugar = sugar;
        this.sellname = sellname;
        this.price = price;
        this.proname = proname;

    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrderdetailid() {
        return orderdetailid;
    }

    public void setOrderdetailid(String orderdetailid) {
        this.orderdetailid = orderdetailid;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getIce() {
        return ice;
    }

    public void setIce(String ice) {
        this.ice = ice;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getSellname() {
        return sellname;
    }

    public void setSellname(String sellname) {
        this.sellname = sellname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
