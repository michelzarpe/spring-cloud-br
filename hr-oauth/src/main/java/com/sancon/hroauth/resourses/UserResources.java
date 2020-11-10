package com.sancon.hroauth.resourses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sancon.hroauth.entities.User;
import com.sancon.hroauth.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/users")
public class UserResources {

	@Autowired
	private UserService service;

	@HystrixCommand(fallbackMethod = "findByEmailAlternative")
	@GetMapping(value = "/search")
	public ResponseEntity<User> findByEmail(@RequestParam(name = "email") String email){
		try {
		User user = service.findByEmail(email);
		log.error("Microsservico faund");
		  return ResponseEntity.ok(user);
		}catch (IllegalArgumentException e) {
		  return ResponseEntity.status(HttpStatus.NOT_FOUND).build();		 	
		}
	}
	
	//alternativa quando não funcionar o caminho principal, pois tem conexão com outro micro serviço
	public ResponseEntity<User> findByEmailAlternative(String email){
		log.error("Microsservico not faund");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	
}
