package com.shellinglaptop.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("orderId")
    @Expose
    private Long orderId;

    @SerializedName("laptopId")
    @Expose
    private Long laptopId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("priceStr")
    @Expose
    private String priceStr;

        public Order(Long laptopId, String name, String address, String phone, int count, String priceStr) {
        this.laptopId = laptopId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.count = count;
        this.priceStr = priceStr;
    }
}
