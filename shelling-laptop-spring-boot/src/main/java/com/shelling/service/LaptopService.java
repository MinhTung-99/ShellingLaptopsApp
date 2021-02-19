package com.shelling.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shelling.entity.Laptop;
import com.shelling.repository.LaptopRepository;

@Service
public class LaptopService {
	
	@Autowired
	private LaptopRepository laptopRepository;

	public List<Laptop> getLaptops(){
		return laptopRepository.findAll();
	}
	public void saveLaptop(Laptop laptop) {
		laptopRepository.save(laptop);
	}
	public void delete(Laptop laptop) {
		laptopRepository.delete(laptop);
	}
}
