package com.shelling.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Pay")
public class PayEntity extends PersonEntity{
	
	@Column
	private String image;
	
	@Column
	private String name;
	
	@Column
	private String hardDrive;
	
	@Column
	private String ram;
	
	@Column
	private long price;
	
	@Column
	private String amount;
	
	@Column
	private long intoMoney;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public long getIntoMoney() {
		return intoMoney;
	}
	public void setIntoMoney(long intoMoney) {
		this.intoMoney = intoMoney;
	}
}
