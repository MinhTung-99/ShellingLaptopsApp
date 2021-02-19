package com.shellinglaptop.model;

import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

public class Laptop implements Serializable {

    @SerializedName("laptopId")
    @Expose
    private Long laptopId;
    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    private String name;
    @SerializedName("image")
    @Expose
    @ColumnInfo(name = "image")
    private String image;
    @SerializedName("price")
    @Expose
    @ColumnInfo(name = "price")
    private Long price;
    @SerializedName("sale")
    @Expose
    private String sale;
    @SerializedName("description")
    @Expose
    private String description;

    private String typeUpdate;
    private String priceStr;

    public Long getLaptopId() {
        return laptopId;
    }
    public void setLaptopId(Long laptopId) {
        this.laptopId = laptopId;
    }
    public String getPriceStr() {
        return priceStr;
    }
    public void setPriceStr(String priceStr) {
        this.priceStr = priceStr;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public Long getPrice() {
        return price;
    }
    public void setPrice(Long price) {
        this.price = price;
    }
    public String getSale() {
        return sale;
    }
    public void setSale(String sale) {
        this.sale = sale;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTypeUpdate() {
        return typeUpdate;
    }
    public void setTypeUpdate(String typeUpdate) {
        this.typeUpdate = typeUpdate;
    }
}
