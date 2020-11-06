package com.sancon.hrpayroll.entities;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

	public Payment getPayment(long workerId, int days) {
		return new Payment(1L, "Bob", 200.00, days);
	}
}
