package com.shelling.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shelling.entity.User;
import com.shelling.service.UserService;

@RestController
public class UserApi {
	
	@Autowired
	private UserService service;
	
	@GetMapping(value = "api-login")
	public List<User> getLogins() {
		return service.getLogins();
	}
	
	@PostMapping(value = "/login")
	public String login(@RequestBody User user) {
		
		System.out.println(user.getUserName() + "===" +user.getPassword());
		
		return service.login(user);
	}
}
