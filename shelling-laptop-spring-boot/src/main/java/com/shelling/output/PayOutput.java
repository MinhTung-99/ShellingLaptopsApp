package com.shelling.output;

import java.util.List;

import com.shelling.dto.PayDTO;

public class PayOutput {
	private List<PayDTO> pay;

	public List<PayDTO> getPay() {
		return pay;
	}

	public void setPay(List<PayDTO> pay) {
		this.pay = pay;
	}
}
