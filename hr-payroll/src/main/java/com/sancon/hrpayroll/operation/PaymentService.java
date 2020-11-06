package com.sancon.hrpayroll.operation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private Environment env;
	
	public Payment getPayment(long workerId, int days) {
		String workerHost = env.getProperty("hr-worker.host");
		log.warn(String.format("HOST Worker: %s", workerHost));
		Map<String,String> uriVariables = new HashMap<>();
		uriVariables.put("id", ""+workerId);
		Worker worker = restTemplate.getForObject(workerHost+"/workers/{id}", Worker.class,uriVariables);
		log.warn("Retornando.....Payment");
		return new Payment(1L, worker.getName(), worker.getDailyIncome(), days);
	}
}
