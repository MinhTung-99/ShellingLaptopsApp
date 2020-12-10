package com.shelling.repository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Account {
	
	@OneToMany
	private Long account_id;
	private String account;
	private String type_account;
	private String password;
	
	public Account() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getType_account() {
		return type_account;
	}
	public void setType_account(String type_account) {
		this.type_account = type_account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
