package com.shelling.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shelling.converter.LaptopConverter;
import com.shelling.dto.LaptopDTO;
import com.shelling.entity.LaptopEntity;
import com.shelling.repository.LaptopRepository;
import com.shelling.service.ILaptopService;

@Service
public class LaptopService implements ILaptopService{

	@Autowired
	private LaptopRepository laptopRepository;
	
	@Autowired
	private LaptopConverter laptopConverter;
	
	@Override
	public LaptopDTO save(LaptopDTO laptopDTO) {
		LaptopEntity laptopEntity = new LaptopEntity();
		laptopEntity = laptopConverter.toEntity(laptopDTO);	
		
		laptopEntity = laptopRepository.save(laptopEntity);
		
		return laptopConverter.toDTO(laptopEntity);
	}

	@Override
	public LaptopDTO update(LaptopDTO laptopDTO) {
		LaptopEntity oldLaptopEntity = laptopRepository.findOne(laptopDTO.getId());
		LaptopEntity newLaptopEntity = laptopConverter.toEntity(laptopDTO, oldLaptopEntity);
	
		newLaptopEntity = laptopRepository.save(newLaptopEntity);
		
		return laptopConverter.toDTO(newLaptopEntity);
	}

	@Override
	public void delete(long[] ids) {
		for(long items : ids) {
			laptopRepository.delete(items);
		}
	}

	@Override
	public List<LaptopDTO> findAll() {
		List<LaptopDTO> results = new ArrayList<>();
		List<LaptopEntity> entities = laptopRepository.findAll();
		
		for(LaptopEntity item : entities) {
			LaptopDTO laptopDTO = laptopConverter.toDTO(item);
			results.add(laptopDTO);
		}
		
		return results;
	}

}
