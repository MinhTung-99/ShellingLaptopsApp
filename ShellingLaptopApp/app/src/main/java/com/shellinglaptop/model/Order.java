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
    @SerializedName("userId")
    @Expose
    private Long userId;
    @SerializedName("count")
    @Expose
    private Long count;
    @SerializedName("totalMoney")
    @Expose
    private String totalMoney;

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getLaptopId() {
        return laptopId;
    }
    public void setLaptopId(Long laptopId) {
        this.laptopId = laptopId;
    }
    public Long getCount() {
        return count;
    }
    public void setCount(Long count) {
        this.count = count;
    }
    public String getTotalMoney() {
        return totalMoney;
    }
    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
