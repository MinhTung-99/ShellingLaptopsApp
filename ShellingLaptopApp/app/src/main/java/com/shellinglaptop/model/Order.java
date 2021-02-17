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

    @SerializedName("laptop")
    @Expose
    private Laptop laptop;

    @SerializedName("userid")
    @Expose
    private User user;

    @SerializedName("count")
    @Expose
    private Long count;


    private String priceStr;
}
