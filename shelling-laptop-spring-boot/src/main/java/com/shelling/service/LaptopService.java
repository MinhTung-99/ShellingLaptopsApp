package com.shelling.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shelling.repository.Laptop;
import com.shelling.repository.LaptopRepository;
import com.shelling.repository.Order;
import com.shelling.repository.OrderRepository;

@Service
public class LaptopService {
	
	@Autowired
	private LaptopRepository laptopRepository;
	
	@Autowired
	private OrderRepository storeRepository;
	
	//-------------LAPTOP--------------------
	public List<Laptop> getLaptops(){
		return laptopRepository.findAll();
	}
	
	public void saveLaptop(Laptop laptop) {
		laptopRepository.save(laptop);
	}
	
	public Laptop getLaptop(Long id) {
		return laptopRepository.findById(id).get();
	}
	
	public void deleteLaptop(Long id) {
		laptopRepository.deleteById(id);
	}
	
	//------------Order--------------------
	public List<Order> getOrders(){
		return storeRepository.findAll();
	}
	public Order saveOrder(Order order) {
		return storeRepository.save(order);
	}
	public void deleteOrder(Long orderId) {
		storeRepository.deleteById(orderId);
	}
}
