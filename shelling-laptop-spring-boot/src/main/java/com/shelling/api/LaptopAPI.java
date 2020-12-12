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

	//-------------ACCCOUNT--------------------
	@PostMapping("/login")
	public Account loginAccount(@RequestBody Account account) {
		List<Account> accounts = service.getAccounts();
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getAccount().equals(account.getAccount()) 
					&& accounts.get(i).getPassword().equals(account.getPassword())) {
				accounts.get(i).setLogin(true);
				service.saveAccount(accounts.get(i));
				return accounts.get(i);
			}
		}
		
		return null;
	}
	@PostMapping("/register")
	public boolean registerAccount(@RequestBody Account account) {
		if(service.saveAccount(account) != null)
			return true;
		
		return false;
	}
	@PostMapping("/logout")
	public boolean logoutAccount(@RequestBody Account account) {
		Account acc = service.getAccount(account.getAccountId());
		acc.setLogin(false);
		if(service.saveAccount(acc) != null)
			return true;
		
		return false;
	}
	
	@GetMapping("/laptop")
	public List<Laptop> showLaptop() {
		return service.getLaptops();
	}
	
	//-------------CART--------------------
	@PostMapping("/cart")
	public List<Laptop> showCart(@RequestBody Account account){
		List<Laptop> laptops = service.getLaptops();
		List<Cart> carts = service.getCarts();
		if(carts.size() == 0)
			return null;
		
		List<Laptop> laptopCarts = new ArrayList<>();
		for(int i = 0; i < laptops.size(); i++) {
			for(int j = 0; j < carts.size(); j++) {
				if(carts.get(j).getAccountId() == account.getAccountId()) {
					if(laptops.get(i).getLaptopId() == carts.get(j).getLaptopId()) {
						laptops.get(i).setCount(carts.get(i).getCount());
						laptopCarts.add(laptops.get(i));
					}	
				}
			}
		}

		return laptopCarts;
	}
	@PostMapping("/insertcart")
	public boolean insertCart(@RequestBody Cart cart) {
		if(service.saveCart(cart) != null)
			return true;
		
		return false;
	}
	@PostMapping("/updatecart")
	public boolean updateCart(@RequestBody Cart cart) {
		Cart c = service.getCart(cart.getCartId());
		c.setCount(cart.getCount());
		if(service.saveCart(c) != null)
			return true;
			
		return false;
	}
	@PostMapping("/deletecart")
	public void deleteCart(@RequestBody Cart cart) {
		service.deleteCart(cart.getCartId());
	}
	
	//-------------STORE--------------------
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
	@PostMapping("/storelaptop")
	public List<Laptop> showStoreLaptop(@RequestBody Account account){
		List<Store> stores = service.getStores();
		List<Laptop> laptops = service.getLaptops();
		
		List<Laptop> laptopStores = new ArrayList<>();
		for(int i = 0; i < stores.size(); i++) {
			if(stores.get(i).getAccountId() == account.getAccountId()) {
				for(int j = 0; j < laptops.size(); j++) {
					if(stores.get(i).getLaptopId() == laptops.get(j).getLaptopId()) {
						laptopStores.add(laptops.get(j));
					}
				}
			}
		}
		
		return laptopStores;
	}
	@PostMapping("/insertstore")
	public boolean insertStore(@RequestBody Store store) {
		if(service.saveStore(store) != null)
			return true;
		
		return false;
	}
}
