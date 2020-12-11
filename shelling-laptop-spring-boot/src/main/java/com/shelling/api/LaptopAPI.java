package com.shelling.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shelling.repository.Account;
import com.shelling.repository.Cart;
import com.shelling.repository.Laptop;
import com.shelling.repository.Store;
import com.shelling.service.LaptopService;

@RestController
public class LaptopApi {
	
	@Autowired
	private LaptopService service;

	@PostMapping("/account")
	public Account postAccountLogin(@RequestBody Account account) {
		List<Account> accounts = service.getAccounts();
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getAccount().equals(account.getAccount()) 
					&& accounts.get(i).getPassword().equals(account.getPassword())) {
				
				return accounts.get(i);
			}
		}
		
		return null;
	}
	
	@GetMapping("/laptop")
	public List<Laptop> showLaptop() {
		return service.getLaptops();
	}
	
	@PostMapping("/cart")
	public List<Laptop> showCart(@RequestBody Account account){
		System.out.println(account.getAccountId());
		List<Laptop> laptops = service.getLaptops();
		List<Cart> carts = service.getCarts();
		
		List<Laptop> laptopCarts = new ArrayList<>();
		for(int i = 0; i < laptops.size(); i++) {
			for(int j = 0; j < carts.size(); j++) {
				if(carts.get(j).getAccountId() == account.getAccountId()) {
					if(laptops.get(i).getLaptopId() == carts.get(j).getLaptopId()) {
						laptopCarts.add(laptops.get(i));
					}	
				}
			}
		}
		
		return laptopCarts;
	}
	
	@PostMapping("/store")
	public List<Store> showStore(@RequestBody Account account){
		List<Store> stores = service.getStores();
		
		List<Store> accountStores = new ArrayList<>();
		for(int i = 0; i < stores.size(); i++) {
			if(stores.get(i).getAccountId() == account.getAccountId()) {
				accountStores.add(stores.get(i));
			}
		}
		
		return accountStores;
	}
}
