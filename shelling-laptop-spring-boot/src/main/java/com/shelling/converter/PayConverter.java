package com.shelling.converter;

import org.springframework.stereotype.Component;

import com.shelling.dto.PayDTO;
import com.shelling.entity.PayEntity;

@Component
public class PayConverter {
	
	public PayEntity toEntity(PayDTO dto) {
		PayEntity entity = new PayEntity();
		
		entity.setName(dto.getName());
		entity.setHardDrive(dto.getHardDrive());
		entity.setRam(dto.getRam());
		entity.setPrice(dto.getPrice());
		entity.setAmount(dto.getAmount());
		entity.setIntoMoney(dto.getIntoMoney());
		
		return entity;
	}
	
	public PayDTO toDTO(PayEntity entity) {
		PayDTO dto = new PayDTO();
		
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
