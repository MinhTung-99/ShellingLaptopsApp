package com.shelling.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
