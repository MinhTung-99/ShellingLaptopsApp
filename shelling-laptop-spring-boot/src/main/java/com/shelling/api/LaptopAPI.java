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
import com.shelling.entity.User;
import com.shelling.repository.LaptopArray;
import com.shelling.service.LaptopService;
import com.shelling.service.UserService;

import util.PriceUtil;

@RestController
class LaptopApi {
	
	@Autowired
	private LaptopService service;
	@Autowired
	private UserService userService;
	
	@GetMapping("/api-laptop")
	public LaptopArray showLaptop() {
		LaptopArray laptopArr = new LaptopArray();
		laptopArr.setLaptops(service.getLaptops());
		return laptopArr;
	}
	@PostMapping(value = "/api-new-laptop/{userName}/{password}")
	public boolean saveLaptop(@RequestBody Laptop laptop, 
			@PathVariable("userName") String userName, @PathVariable("password") String password) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		String typeLogin = userService.login(user);
		if(typeLogin.equals("ADMIN")) {
			service.saveLaptop(laptop);
			return true;
		}
		
		return false;
	}
	@PutMapping(value = "/api-update-laptop/{laptopId}/{userName}/{password}")
	public boolean updateLaptop(@RequestBody Laptop laptop,  @PathVariable("laptopId") Long laptopid,
			@PathVariable("userName") String userName, @PathVariable("password") String password) {
		laptop.setLaptopId(laptopid);
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		String typeLogin = userService.login(user);
		if(typeLogin.equals("ADMIN")) {
			service.saveLaptop(laptop);
			return true;
		}
		return false;
	}
	@DeleteMapping(value = "/delete/{userName}/{password}")
	public boolean deleteNew(@RequestBody Laptop laptop,
			@PathVariable("userName") String userName, @PathVariable("password") String password) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		String typeLogin = userService.login(user);
		if(typeLogin.equals("ADMIN")) {
			service.delete(laptop);
			return true;
		}
		
		return false;
	}
	
//	@PostMapping("/neworder")
//	public Order newOrder(@RequestBody Order order) {
//		return service.saveOrder(order);
//	}
}
