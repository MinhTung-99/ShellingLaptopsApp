package com.shelling.output;

import java.util.List;

import com.shelling.dto.LaptopDTO;

public class LaptopOutput {
	private List<LaptopDTO> laptops;

	public List<LaptopDTO> getLaptops() {
		return laptops;
	}

	public void setLaptops(List<LaptopDTO> laptops) {
		this.laptops = laptops;
	}
}
