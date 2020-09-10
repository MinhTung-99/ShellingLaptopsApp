package com.shelling.service;

import java.util.List;

import com.shelling.dto.PayDTO;

public interface IPayService {
	PayDTO save(PayDTO payDTO);
	PayDTO update(PayDTO payDTO);
	void delete(long[] ids);
	List<PayDTO> findAll();
}
