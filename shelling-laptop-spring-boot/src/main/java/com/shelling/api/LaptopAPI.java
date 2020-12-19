package com.shelling.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shelling.repository.Laptop;
import com.shelling.repository.LaptopArray;
import com.shelling.repository.Order;
import com.shelling.service.LaptopService;

@RestController
public class LaptopApi {
	
	@Autowired
	private LaptopService service;
	
	@GetMapping("/laptop")
	public LaptopArray showLaptop() {
		LaptopArray laptopArr = new LaptopArray();
		laptopArr.setLaptops(service.getLaptops());
		return laptopArr;
	}
	
	@PostMapping("/neworder")
	public Order newOrder(@RequestBody Order order) {
		return service.saveOrder(order);
	}
}
