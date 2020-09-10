package com.shelling.service;

import java.util.List;

import com.shelling.dto.LaptopDTO;

public interface ILaptopService {
	LaptopDTO save(LaptopDTO laptopDTO);
	LaptopDTO update(LaptopDTO laptopDTO);
	void delete(long[] ids);
	List<LaptopDTO> findAll();
}
