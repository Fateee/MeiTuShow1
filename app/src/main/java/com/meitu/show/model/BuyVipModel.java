package com.meitu.show.model;

import com.meitu.show.presenter.base.BaseModelInf;

public class BuyVipModel implements BaseModelInf {

    public static final int PRODUCT = 1;
    public static final int INFO = 2;
    private String proName;

    private int days;

    private double price;

    private int type;

    public BuyVipModel(String proName, int days, double price, int type) {
        this.proName = proName;
        this.days = days;
        this.price = price;
        this.type = type;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
