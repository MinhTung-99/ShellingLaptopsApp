package com.shelling.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaptopService {
	
	@Autowired
    private CartRepository cartRepository;
	
	@Autowired
	private LaptopRepository laptopRepository;
	
	public List<Cart> getCarts(){
		return cartRepository.findAll();
	}
	
	public List<Laptop> getLaptops(){
		return laptopRepository.findAll();
	}
	
	public void save(Laptop laptop) {
		laptopRepository.save(laptop);
	}
	
	public Laptop get(Long id) {
		return laptopRepository.findById(id).get();
	}
	
	public void delete(Long id) {
		laptopRepository.deleteById(id);
	}
}
