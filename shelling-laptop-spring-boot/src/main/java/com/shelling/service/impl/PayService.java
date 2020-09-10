package com.shelling.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shelling.converter.PayConverter;
import com.shelling.dto.PayDTO;
import com.shelling.entity.PayEntity;
import com.shelling.repository.PayRepository;
import com.shelling.service.IPayService;

@Service
public class PayService implements IPayService{

	@Autowired
	private PayRepository payRepository;
	
	@Autowired
	private PayConverter payConverter;
	
	@Override
	public PayDTO save(PayDTO payDTO) {
		PayEntity payEntity = new PayEntity();
		payEntity = payConverter.toEntity(payDTO);	
	
		payEntity = payRepository.save(payEntity);
		
		return payConverter.toDTO(payEntity);
	}

	@Override
	public PayDTO update(PayDTO payDTO) {
		PayEntity oldPayEntity = payRepository.findOne(payDTO.getId());
		PayEntity newPayEntity = payConverter.toEntity(payDTO, oldPayEntity);
		
		newPayEntity = payRepository.save(newPayEntity);
		
		return payConverter.toDTO(newPayEntity);
	}

	@Override
	public void delete(long[] ids) {
		for(long items : ids) {
			payRepository.delete(items);
		}
	}

	@Override
	public List<PayDTO> findAll() {
		List<PayDTO> results = new ArrayList<>();
		List<PayEntity> entities = payRepository.findAll();
		
		for(PayEntity item : entities) {
			PayDTO payDTO = payConverter.toDTO(item);
			results.add(payDTO);
		}
		
		return results;
	}

}
