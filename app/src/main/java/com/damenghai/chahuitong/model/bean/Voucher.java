package com.damenghai.chahuitong.model.bean;

import com.damenghai.chahuitong.utils.DateUtils;
import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by Sgun on 15/10/15.
 */
public class Voucher implements Serializable {
    @Expose
    String voucher_order_id;
    @Expose
    String voucher_type;
    @Expose
    long voucher_start_date;
    @Expose
    long voucher_end_date;
    @Expose
    String voucher_t_id;
    @Expose
    String voucher_id;
    @Expose
    String voucher_owner_name;
    @Expose
    String voucher_store_id;
    @Expose
    String voucher_desc;
    @Expose
    String voucher_owner_id;
    @Expose
    String voucher_price;
    @Expose
    String voucher_title;
    @Expose
    String voucher_state;
    @Expose
    String voucher_code;
    @Expose
    String voucher_limit;
    @Expose
    String voucher_active_date;

    public String getVoucher_active_date() {
        return voucher_active_date;
    }

    public void setVoucher_active_date(String voucher_active_date) {
        this.voucher_active_date = voucher_active_date;
    }

    public String getVoucher_limit() {
        return voucher_limit;
    }

    public void setVoucher_limit(String voucher_limit) {
        this.voucher_limit = voucher_limit;
    }

    public String getVoucher_code() {
        return voucher_code;
    }

    public void setVoucher_code(String voucher_code) {
        this.voucher_code = voucher_code;
    }

    public String getVoucher_state() {
        return voucher_state;
    }

    public void setVoucher_state(String voucher_state) {
        this.voucher_state = voucher_state;
    }

    public String getVoucher_title() {
        return voucher_title;
    }

    public void setVoucher_title(String voucher_title) {
        this.voucher_title = voucher_title;
    }

    public String getVoucher_price() {
        return voucher_price;
    }

    public void setVoucher_price(String voucher_price) {
        this.voucher_price = voucher_price;
    }

    public String getVoucher_owner_id() {
        return voucher_owner_id;
    }

    public void setVoucher_owner_id(String voucher_owner_id) {
        this.voucher_owner_id = voucher_owner_id;
    }

    public String getVoucher_desc() {
        return voucher_desc;
    }

    public void setVoucher_desc(String voucher_desc) {
        this.voucher_desc = voucher_desc;
    }

    public String getVoucher_end_date() {
        return DateUtils.getDate(voucher_end_date);
    }

    public void setVoucher_end_date(long voucher_end_date) {
        this.voucher_end_date = voucher_end_date;
    }

    public String getVoucher_store_id() {
        return voucher_store_id;
    }

    public void setVoucher_store_id(String voucher_store_id) {
        this.voucher_store_id = voucher_store_id;
    }

    public String getVoucher_owner_name() {
        return voucher_owner_name;
    }

    public void setVoucher_owner_name(String voucher_owner_name) {
        this.voucher_owner_name = voucher_owner_name;
    }

    public String getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(String voucher_id) {
        this.voucher_id = voucher_id;
    }

    public String getVoucher_t_id() {
        return voucher_t_id;
    }

    public void setVoucher_t_id(String voucher_t_id) {
        this.voucher_t_id = voucher_t_id;
    }

    public String getVoucher_start_date() {
        return DateUtils.getDate(voucher_start_date);
    }

    public void setVoucher_start_date(long voucher_start_date) {
        this.voucher_start_date = voucher_start_date;
    }

    public String getVoucher_type() {
        return voucher_type;
    }

    public void setVoucher_type(String voucher_type) {
        this.voucher_type = voucher_type;
    }

    public String getVoucher_order_id() {
        return voucher_order_id;
    }

    public void setVoucher_order_id(String voucher_order_id) {
        this.voucher_order_id = voucher_order_id;
    }

}
