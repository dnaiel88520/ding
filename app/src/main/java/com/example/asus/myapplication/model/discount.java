package com.example.asus.myapplication.model;

public class discount {
    private String context;
    private String status;
    private String time;
    private String name;
    public discount() {
    }

    public discount(String context, String status, String time,String name) {
        this.context = context;
        this.status = status;
        this.time = time;
        this.name =name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
