package com.shelling.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shelling.dto.PayDTO;
import com.shelling.output.PayOutput;
import com.shelling.service.IPayService;

@RestController
public class PayApi {
	
	@Autowired
	private IPayService payService;
	
	@GetMapping(value = "/pay")
	public PayOutput showPay() {
		PayOutput result = new PayOutput();
		  
		result.setPay(payService.findAll());
		  
	    return result;
	}
	
	@PostMapping(value = "/pay")
	public PayDTO createPay(@RequestBody PayDTO payDTO) {
		return payService.save(payDTO);
	}
	
	@PutMapping(value = "/pay/{id}")
	public PayDTO updatePay(@RequestBody PayDTO payDTO, @PathVariable("id") long id) {
		payDTO.setId(id);
	    return payService.update(payDTO);
	}
	
	@DeleteMapping(value = "/pay")
	public void deleteNew(@RequestBody long[] ids) {
		payService.delete(ids);
	}
}
