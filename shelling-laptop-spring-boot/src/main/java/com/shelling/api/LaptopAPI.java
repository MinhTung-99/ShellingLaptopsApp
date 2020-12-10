package com.shelling.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shelling.repository.Cart;
import com.shelling.repository.Laptop;
import com.shelling.repository.LaptopService;

@RestController
public class LaptopApi {
	
	@Autowired
	private LaptopService service;

	
	@GetMapping("/laptop")
	public List<Laptop> showLaptop() {
		return service.getLaptops();
	}
	
	@GetMapping("/cart/{account_id}")
	public List<Laptop> showCart(@PathVariable("id") String id){
		
//		List<Laptop> laptops = service.getLaptops();
//		List<Cart> carts = service.getCarts();
//		
//		List<Long> laptopIDs = new ArrayList<>();
//		for(int i = 0; i < carts.size(); i++) {
//			if(carts.get(i).getAccountId() == id) {
//				laptopIDs.add(laptops.get(i).getLaptopId());
//			}
//		}
//		
		List<Laptop> laptopCarts = new ArrayList<>();
//		for(int i = 0; i < laptops.size(); i++) {
//			laptopCarts.add(laptops.get(i));
//		}
		
		return laptopCarts;
	}
}
