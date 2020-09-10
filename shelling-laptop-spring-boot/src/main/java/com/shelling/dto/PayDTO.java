package com.shelling.dto;

public class PayDTO extends PersonDTO{
	private String name;
	private String hardDrive;
	private String ram;
	private double price;
	private String amount;
	private String intoMoney;
	
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getIntoMoney() {
		return intoMoney;
	}
	public void setIntoMoney(String intoMoney) {
		this.intoMoney = intoMoney;
	}
}
