package com.damenghai.chahuitong.model.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class OrderGroup implements Serializable {

    @Expose
    private ArrayList<Order> order_list;
    @Expose
    private String pay_amount;
    @Expose
    private String add_time;
    @Expose
    private String pay_sn;

    public ArrayList<Order> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(ArrayList<Order> order_list) {
        this.order_list = order_list;
    }

    public String getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(String pay_amount) {
        this.pay_amount = pay_amount;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getPay_sn() {
        return pay_sn;
    }

    public void setPay_sn(String pay_sn) {
        this.pay_sn = pay_sn;
    }

}
