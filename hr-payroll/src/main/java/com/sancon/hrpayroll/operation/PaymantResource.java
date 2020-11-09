package com.sancon.hrpayroll.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value = "/paymants")
public class PaymantResource {
	
	@Autowired
	public PaymentService service;
	
	@HystrixCommand(fallbackMethod = "getPaymentAlternative")
	@GetMapping(value = "/{workerId}/days/{days}")
	public ResponseEntity<Payment> getPayment(@PathVariable(name = "workerId") Long workerId, @PathVariable(name = "days") Integer days ){
		Payment obj = service.getPayment(workerId, days);
		return ResponseEntity.ok(obj);
	}

	//alternativa quando não funcionar o caminho principal, pois tem conexão com outro micro serviço
	public ResponseEntity<Payment> getPaymentAlternative(Long workerId, Integer days){
		Payment p = new Payment(null, "CLeiton", 400.00, days);
		return ResponseEntity.ok(p);
	}
	
}
