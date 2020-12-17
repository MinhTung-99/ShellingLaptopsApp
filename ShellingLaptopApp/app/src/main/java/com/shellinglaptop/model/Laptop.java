package com.shellinglaptop.model;

import androidx.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Laptop {

    @SerializedName("laptopId")
    @Expose
    private Long laptopId;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cpu")
    @Expose
    private String cpu;
    @SerializedName("cardGraphic")
    @Expose
    private String cardGraphic;
    @SerializedName("hardDrive")
    @Expose
    private String hardDrive;
    @SerializedName("ram")
    @Expose
    private String ram;
    @SerializedName("display")
    @Expose
    private String display;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("os")
    @Expose
    private String os;
    @SerializedName("pin")
    @Expose
    private String pin;

    @SerializedName("price")
    @Expose
    private float price;

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
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
