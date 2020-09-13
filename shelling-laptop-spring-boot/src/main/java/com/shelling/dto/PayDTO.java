package com.shelling.dto;

public class PayDTO extends PersonDTO{
	private String image;
	private String name;
	private String hardDrive;
	private String ram;
	private long price;
	private String amount;
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
