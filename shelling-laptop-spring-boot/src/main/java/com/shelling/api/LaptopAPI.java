package com.shelling.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shelling.dto.LaptopDTO;
import com.shelling.output.LaptopOutput;
import com.shelling.service.ILaptopService;

@RestController
public class LaptopAPI {

	@Autowired
	private ILaptopService laptopService;
	
	@GetMapping(value = "/laptop")
	public LaptopOutput showLaptop() {
		LaptopOutput result = new LaptopOutput();
		  
		result.setLaptops(laptopService.findAll());
		  
	    return result;
	}
	
	
//	@PostMapping(value = "/laptop")
//	public LaptopDTO createLaptop(@RequestBody LaptopDTO laptopDTO) {
//		return laptopService.save(laptopDTO);
//	}
//	
//	@PutMapping(value = "/laptop/{id}")
//	public LaptopDTO updateLaptop(@RequestBody LaptopDTO laptopDTO, @PathVariable("id") long id) {
//		laptopDTO.setId(id);
//	    return laptopService.update(laptopDTO);
//	}
//	
//	@DeleteMapping(value = "/laptop")
//	public void deleteLaptop(@RequestBody long[] ids) {
//		laptopService.delete(ids);
//	}
}
