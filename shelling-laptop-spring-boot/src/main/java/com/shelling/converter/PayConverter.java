package com.shelling.converter;

import org.springframework.stereotype.Component;

import com.shelling.dto.PayDTO;
import com.shelling.entity.PayEntity;

@Component
public class PayConverter {
	
	public PayEntity toEntity(PayDTO dto) {
		PayEntity entity = new PayEntity();
		
		entity.setImage(dto.getImage());
		entity.setName(dto.getName());
		entity.setHardDrive(dto.getHardDrive());
		entity.setRam(dto.getRam());
		entity.setPrice(dto.getPrice());
		entity.setAmount(dto.getAmount());
		entity.setIntoMoney(dto.getIntoMoney());
		entity.setId(dto.getId());
		entity.setFullName(dto.getFullName());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setEmail(dto.getEmail());
		entity.setAddress(dto.getAddress());
		
		return entity;
	}
	
	public PayDTO toDTO(PayEntity entity) {
		PayDTO dto = new PayDTO();
		
		dto.setImage(entity.getImage());
		dto.setName(entity.getName());
		dto.setHardDrive(entity.getHardDrive());
		dto.setRam(entity.getRam());
		dto.setPrice(entity.getPrice());
		dto.setAmount(entity.getAmount());
		dto.setIntoMoney(entity.getIntoMoney());
		dto.setId(entity.getId());
		dto.setFullName(entity.getFullName());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setEmail(entity.getEmail());
		dto.setAddress(entity.getAddress());
		
		return dto;
	}
	
	public PayEntity toEntity(PayDTO dto, PayEntity entity) {
		
		entity.setName(dto.getName());
		entity.setHardDrive(dto.getHardDrive());
		entity.setRam(dto.getRam());
		entity.setPrice(dto.getPrice());
		entity.setAmount(dto.getAmount());
		entity.setIntoMoney(dto.getIntoMoney());
		
		return entity;
	}
}
