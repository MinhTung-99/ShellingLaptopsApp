package com.shelling.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shelling.entity.Laptop;
import com.shelling.entity.LaptopArray;
import com.shelling.entity.User;
import com.shelling.service.LaptopService;
import com.shelling.service.UserService;

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
		User dbUser = userService.login(user);
		if(dbUser.getTypeUser().equals("ADMIN")) {
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
		User dbUser = userService.login(user);
		if(dbUser.getTypeUser().equals("ADMIN")) {
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
		User dbUser = userService.login(user);
		if(dbUser.getTypeUser().equals("ADMIN")) {
			service.delete(laptop);
			return true;
		}
		
		return false;
	}
}
