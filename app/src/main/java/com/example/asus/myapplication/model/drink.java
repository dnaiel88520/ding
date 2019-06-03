package com.example.asus.myapplication.model;

public class drink {

    private String proname;
    private String sellname;
    private String proprice;
    private String image_URL;
    private String prodata;

    public drink() {
    }

    public drink( String proname, String sellname, String proprice, String image_URL,String prodata) {

        this.proname = proname;
        this.sellname = sellname;
        this.proprice = proprice;
        this.image_URL = image_URL;
        this.prodata = prodata;
    }

    public String getProname() {
        return proname;
    }

    public String getSellname() {
        return sellname;
    }

    public String getProprice() {
        return proprice;
    }

    public String getImage_URL() {
        return image_URL;
    }
    public String getProdata() {
        return prodata;
    }



    public void setProname(String proname) {
        this.proname = proname;
    }

    public void setSellname(String sellname) {
        this.sellname = sellname;
    }

    public void setProprice(String proprice) {
        this.proprice = proprice;
    }

    public void setImage_URL(String image_URL) {
        this.image_URL = image_URL;

    }
    public void setProdata(String prodata) {
        this.prodata = prodata;
    }

}
