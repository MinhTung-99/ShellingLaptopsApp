package com.shelling.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shelling.entity.Laptop;
import com.shelling.entity.Order;
import com.shelling.repository.LaptopArray;
import com.shelling.service.LaptopService;

@RestController
class LaptopApi {
	
	@Autowired
	private LaptopService service;
	
	@GetMapping("/laptop")
	public LaptopArray showLaptop() {
		LaptopArray laptopArr = new LaptopArray();
		laptopArr.setLaptops(service.getLaptops());
		return laptopArr;
	}
	@PostMapping(value = "/api-new-laptop")
	public void saveLaptop(@RequestBody Laptop laptop ) {
		service.saveLaptop(laptop);
	}
	@PutMapping(value = "/api-update-laptop/{laptopId}")
	public void updateLaptop(@RequestBody Laptop laptop,  @PathVariable("laptopId") Long laptopid) {
		laptop.setLaptopId(laptopid);
		service.saveLaptop(laptop);
	}
	@DeleteMapping(value = "/delete")
	public void deleteNew(@RequestBody Laptop laptop) {
		service.delete(laptop);
	}
	
//	@PostMapping("/neworder")
//	public Order newOrder(@RequestBody Order order) {
//		return service.saveOrder(order);
//	}
}
