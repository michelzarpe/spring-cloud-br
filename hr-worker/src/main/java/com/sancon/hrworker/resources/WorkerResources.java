package com.sancon.hrworker.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sancon.hrworker.entities.Worker;
import com.sancon.hrworker.repositories.WorkerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/workers")
public class WorkerResources {
	
	@Autowired
	private Environment env;
	
	@Autowired
	public WorkerRepository repository;
	
	@GetMapping(value="/configs")
	public ResponseEntity<Void> getConfigs(){
		log.info("Config: "+env.getProperty("test.config"));
		return ResponseEntity.noContent().build();
	}

	
	@GetMapping
	public ResponseEntity<List<Worker>> findAll(){
		List<Worker> list = repository.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> findAll(@PathVariable Long id){
		//colocado esse timer para saber se estoura o tempo de resposta do micros serviço
		
		try {
			Thread.sleep(3000L);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		log.info("PORT: "+env.getProperty("local.server.port"));
		Worker obj = repository.findById(id).get();
		return ResponseEntity.ok(obj);
	}
	
}
