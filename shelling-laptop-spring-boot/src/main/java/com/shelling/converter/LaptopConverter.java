package com.shelling.converter;

import org.springframework.stereotype.Component;

import com.shelling.dto.LaptopDTO;
import com.shelling.entity.LaptopEntity;

@Component
public class LaptopConverter {

	public LaptopEntity toEntity(LaptopDTO dto) {
		LaptopEntity entity = new LaptopEntity();
		
		entity.setId(dto.getId());
		entity.setBrand(dto.getBrand());
		entity.setName(dto.getName());
		entity.setCpu(dto.getCpu());
		entity.setCardGraphic(dto.getCardGraphic());
		entity.setHardDrive(dto.getHardDrive());
		entity.setRam(dto.getRam());
		entity.setDisplay(dto.getDisplay());
		entity.setImage(dto.getImage());
		entity.setWeight(dto.getWeight());
		entity.setColor(dto.getColor());
		entity.setOs(dto.getOs());
		entity.setPin(dto.getPin());
		entity.setPrice(dto.getPrice());
		entity.setPriceStr(dto.getPriceStr());
		
		return entity;
	}
	
	public LaptopDTO toDTO(LaptopEntity entity) {
		
		LaptopDTO dto = new LaptopDTO();
		
		dto.setId(entity.getId());
		dto.setBrand(entity.getBrand());
		dto.setName(entity.getName());
		dto.setCpu(entity.getCpu());
		dto.setCardGraphic(entity.getCardGraphic());
		dto.setHardDrive(entity.getHardDrive());
		dto.setRam(entity.getRam());
		dto.setDisplay(entity.getDisplay());
		dto.setImage(entity.getImage());
		dto.setWeight(entity.getWeight());
		dto.setColor(entity.getColor());
		dto.setOs(entity.getOs());
		dto.setPin(entity.getPin());
		dto.setPrice(entity.getPrice());
		dto.setPriceStr(entity.getPriceStr());
		
		return dto;	
	}
	
	public LaptopEntity toEntity(LaptopDTO dto, LaptopEntity entity) {
		
		entity.setId(dto.getId());
		entity.setBrand(dto.getBrand());
		entity.setName(dto.getName());
		entity.setCpu(dto.getCpu());
		entity.setCardGraphic(dto.getCardGraphic());
		entity.setHardDrive(dto.getHardDrive());
		entity.setRam(dto.getRam());
		entity.setDisplay(dto.getDisplay());
		entity.setImage(dto.getImage());
		entity.setWeight(dto.getWeight());
		entity.setColor(dto.getColor());
		entity.setOs(dto.getOs());
		entity.setPin(dto.getPin());
		entity.setPrice(dto.getPrice());
		entity.setPriceStr(dto.getPriceStr());
		
		return entity;
	}
}
