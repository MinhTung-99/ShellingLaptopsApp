package com.shelling.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shelling.entity.Order;
import com.shelling.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> getOrders(){
		return orderRepository.findAll();
	}
	
	public boolean saveOrder(Order order) {
		return orderRepository.save(order) != null;
	}
	
//	public List<Order> getOrders(){
//	return storeRepository.findAll();
//}

//public void deleteOrder(Long orderId) {
//	storeRepository.deleteById(orderId);
//}
}
