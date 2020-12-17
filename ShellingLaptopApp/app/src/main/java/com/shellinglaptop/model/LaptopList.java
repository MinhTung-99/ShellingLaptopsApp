package com.shellinglaptop.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LaptopList {
    @SerializedName("laptops")
    @Expose
    private List<Laptop> laptops = new ArrayList<>();

    public List<Laptop> getLaptops() {
        return laptops;
    }
}
