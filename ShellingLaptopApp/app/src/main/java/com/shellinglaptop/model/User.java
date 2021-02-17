package com.shellinglaptop.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

    @SerializedName("laptopid")
    @Expose
    private Long userId;

    @SerializedName("username")
    @Expose
    private String userName;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("fullname")
    @Expose
    private String fullName;

    @SerializedName("phonenumber")
    @Expose
    private Integer phoneNumber;

    @SerializedName("createddate")
    @Expose
    private Date createdDate;

    @SerializedName("laptopid")
    @Expose
    private List<Order> orders = new ArrayList<>();
}
