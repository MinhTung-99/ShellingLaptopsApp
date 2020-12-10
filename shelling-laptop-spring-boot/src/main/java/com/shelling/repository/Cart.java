package com.shelling.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "cart")
@Entity
public class Cart {
	
	@Id
	@GeneratedValue
	private Long cartId;
	private Long accountId;
	private Long laptopId;
	
	public Cart() {}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getLaptopId() {
		return laptopId;
	}

	public void setLaptopId(Long laptopId) {
		this.laptopId = laptopId;
	}
}
