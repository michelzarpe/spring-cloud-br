package com.sancon.hrpayroll.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sancon.hrpayroll.operation.Worker;

//@FeignClient(name = "hr-worker",url = "localhost:8001", path = "/workers")

@Component
@FeignClient(name = "hr-worker", path = "/workers")
public interface WorkerFeignClient {
	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> findAll(@PathVariable Long id);
}
