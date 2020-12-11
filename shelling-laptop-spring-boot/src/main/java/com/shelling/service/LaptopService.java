package com.shelling.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shelling.repository.Account;
import com.shelling.repository.AccountRepository;
import com.shelling.repository.Cart;
import com.shelling.repository.CartRepository;
import com.shelling.repository.Laptop;
import com.shelling.repository.LaptopRepository;
import com.shelling.repository.Store;
import com.shelling.repository.StoreRepository;

@Service
public class LaptopService {
	
	@Autowired
    private CartRepository cartRepository;
	
	@Autowired
	private LaptopRepository laptopRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	public List<Account> getAccounts(){
		return accountRepository.findAll();
	}
	
	public List<Store> getStores(){
		return storeRepository.findAll();
	}
	
	public List<Cart> getCarts(){
		return cartRepository.findAll();
	}
	
	public List<Laptop> getLaptops(){
		return laptopRepository.findAll();
	}
	
	public void save(Laptop laptop) {
		laptopRepository.save(laptop);
	}
	
	public Laptop get(Long id) {
		return laptopRepository.findById(id).get();
	}
	
	public void delete(Long id) {
		laptopRepository.deleteById(id);
	}
}
