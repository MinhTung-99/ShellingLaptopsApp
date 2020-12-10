package com.shelling.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "laptop")
public class Laptop {
	
	@OneToMany
	private Long laptopId;
	private String brand;
	private String name;
	private String cpu;
	private String cardGraphic;
	private String hardDrive;
	private String ram;
	private String display;
	private String image;
	private String weight;
	private String color;
	private String os;
	private String pin;
	private long price;
	
	public Laptop() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
}
