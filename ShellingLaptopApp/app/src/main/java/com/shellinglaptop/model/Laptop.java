package com.shellinglaptop.model;

import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

// room: @ColumnInfo

public class Laptop implements Serializable {

    @SerializedName("laptopId")
    @Expose
    private Long laptopId;

    @SerializedName("brand")
    @Expose
    @ColumnInfo(name = "bard")
    private String brand;

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("cpu")
    @Expose
    @ColumnInfo(name = "cpu")
    private String cpu;

    @SerializedName("cardGraphic")
    @Expose
    @ColumnInfo(name = "cardGraphic")
    private String cardGraphic;

    @SerializedName("hardDrive")
    @Expose
    @ColumnInfo(name = "hardDrive")
    private String hardDrive;

    @SerializedName("ram")
    @Expose
    @ColumnInfo(name = "ram")
    private String ram;

    @SerializedName("display")
    @Expose
    @ColumnInfo(name = "display")
    private String display;

    @SerializedName("image")
    @Expose
    @ColumnInfo(name = "image")
    private String image;

    @SerializedName("imageUrl")
    @Expose
    @ColumnInfo(name = "imageUrl")
    private String imageUrl;
    @SerializedName("weight")
    @Expose
    @ColumnInfo(name = "weight")
    private String weight;

    @SerializedName("color")
    @Expose
    @ColumnInfo(name = "color")
    private String color;

    @SerializedName("os")
    @Expose
    @ColumnInfo(name = "os")
    private String os;

    @SerializedName("pin")
    @Expose
    @ColumnInfo(name = "pin")
    private String pin;

    @SerializedName("price")
    @Expose
    @ColumnInfo(name = "price")
    private Long price;

    @SerializedName("priceStr")
    @Expose
    private String priceStr;

    public Laptop() {}

    public Long getLaptopId() {
        return laptopId;
    }
    public void setLaptopId(Long laptopId) {
        this.laptopId = laptopId;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCpu() {
        return cpu;
    }
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }
    public String getCardGraphic() {
        return cardGraphic;
    }
    public void setCardGraphic(String cardGraphic) {
        this.cardGraphic = cardGraphic;
    }
    public String getHardDrive() {
        return hardDrive;
    }
    public void setHardDrive(String hardDrive) {
        this.hardDrive = hardDrive;
    }
    public String getRam() {
        return ram;
    }
    public void setRam(String ram) {
        this.ram = ram;
    }
    public String getDisplay() {
        return display;
    }
    public void setDisplay(String display) {
        this.display = display;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getOs() {
        return os;
    }
    public void setOs(String os) {
        this.os = os;
    }
    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }
    public Long getPrice() {
        return price;
    }
    public void setPrice(Long price) {
        this.price = price;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getPriceStr() {
        return priceStr;
    }
    public void setPriceStr(String priceStr) {
        this.priceStr = priceStr;
    }
}
