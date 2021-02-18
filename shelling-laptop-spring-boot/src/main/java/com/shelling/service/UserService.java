package com.shelling.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shelling.entity.User;
import com.shelling.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getLogins(){
		return userRepository.findAll();
	}
	
	public String login(User user) {
		List<User> users = userRepository.findAll();
		for(User data: users) {
			if(user.getUserName().equals(data.getUserName()) 
					&& user.getPassword().equals(data.getPassword())) {
				return data.getTypeUser();
			}
		}
		return "FAILED";
	}
}
