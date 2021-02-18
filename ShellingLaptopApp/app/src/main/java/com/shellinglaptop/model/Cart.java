package com.shellinglaptop.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart")
public class Cart extends Laptop{

    @PrimaryKey
    private Long cartId;

    @ColumnInfo(name = "count")
    private int count;

    @ColumnInfo(name = "totalMoney")
    private Long totalMoney;

    private String totalMoneyStr;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getTotalMoneyStr() {
        return totalMoneyStr;
    }

    public void setTotalMoneyStr(String totalMoneyStr) {
        this.totalMoneyStr = totalMoneyStr;
    }

    public void setCart(Laptop laptop, Cart cart){
        cart.setLaptopId(laptop.getLaptopId());
        cart.setName(laptop.getName());
        cart.setPriceStr(laptop.getPriceStr());
        cart.setPrice(laptop.getPrice());
    }
}
