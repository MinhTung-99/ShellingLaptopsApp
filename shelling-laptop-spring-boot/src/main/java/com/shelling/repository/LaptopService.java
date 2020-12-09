package com.shelling.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaptopService {
	
	@Autowired
	private LaptopRepository repo;
	
	public List<Laptop> getLaptops(){
		return repo.findAll();
	}
	
	public void save(Laptop laptop) {
		repo.save(laptop);
	}
	
	public Laptop get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
