package com.shelling.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.shelling.entity.Laptop;
import com.shelling.entity.Order;
import com.shelling.repository.LaptopRepository;
import com.shelling.repository.OrderRepository;

@Service
public class LaptopService {
	
	@Autowired
	private LaptopRepository laptopRepository;
	
//	@Autowired
//	private OrderRepository storeRepository;
	
	//-------------LAPTOP--------------------
	public List<Laptop> getLaptops(){
		return laptopRepository.findAll();
	}
	public void saveLaptop(Laptop laptop) {
		laptopRepository.save(laptop);
	}
	public void delete(Laptop laptop) {
		laptopRepository.delete(laptop);
	}

//	public Laptop getLaptop(Long id) {
//		return laptopRepository.findById(id).get();
//	}
	
	//------------Order--------------------
//	public List<Order> getOrders(){
//		return storeRepository.findAll();
//	}
//	public Order saveOrder(Order order) {
//		return storeRepository.save(order);
//	}
//	public void deleteOrder(Long orderId) {
//		storeRepository.deleteById(orderId);
//	}
}
