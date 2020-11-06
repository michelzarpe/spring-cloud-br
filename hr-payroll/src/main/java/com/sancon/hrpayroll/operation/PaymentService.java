package com.sancon.hrpayroll.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sancon.hrpayroll.feignclients.WorkerFeignClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentService {

	@Autowired
	private WorkerFeignClient workerFeignClient;
	//private RestTemplate restTemplate;
	
	//@Autowired
	//private Environment env;
	
	public Payment getPayment(long workerId, int days) {
		log.warn("Iniciando.....Payment");
		//RestTemplat
		//String workerHost = env.getProperty("hr-worker.host");
		//log.warn(String.format("HOST Worker: %s", workerHost));
		//Map<String,String> uriVariables = new HashMap<>();
		//uriVariables.put("id", ""+workerId);
		//Worker worker = restTemplate.getForObject(workerHost+"/workers/{id}", Worker.class,uriVariables);
		
		//FeignClients
		Worker worker = workerFeignClient.findAll(workerId).getBody();
		
		log.warn("Retornando.....Payment");
		return new Payment(1L, worker.getName(), worker.getDailyIncome(), days);
	}
}
