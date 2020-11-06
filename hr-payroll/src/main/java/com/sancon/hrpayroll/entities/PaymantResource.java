package com.sancon.hrpayroll.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/paymants")
public class PaymantResource {
	
	@Autowired
	public PaymentService service;
	
	@GetMapping(value = "/{workerId}/days/{days}")
	public ResponseEntity<Payment> getPayment(@PathVariable(name = "workerId") Long workerId, @PathVariable(name = "days") Integer days ){
		Payment obj = service.getPayment(workerId, days);
		return ResponseEntity.ok(obj);
	}


	
}
