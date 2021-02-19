package com.shelling.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shelling.entity.Order;
import com.shelling.entity.User;
import com.shelling.service.OrderService;
import com.shelling.service.UserService;

@RestController
public class OrderApi {

	@Autowired
	private OrderService service;
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "api-order")
	public List<Order> getOrders(){
		return service.getOrders();
	}
	
	@PostMapping("/api-new-order/{userName}/{password}")
	public boolean newOrder(@RequestBody Order order,
			@PathVariable("userName") String userName, @PathVariable("password") String password) {
		
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		User dbUser = userService.login(user);
		if(dbUser.getTypeUser().equals("USER")) {
			return service.saveOrder(order);
		}
		
		return false;
	}
}
