package com.sancon.hroauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sancon.hroauth.entities.User;
import com.sancon.hroauth.feignclients.UserFeignClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	public User findByEmail(String email) {
		User user = userFeignClient.findByEmail(email).getBody();
		if(user ==null) {
			log.error("Email not found: "+email);
			throw new IllegalArgumentException("Email not Found");
		}
		log.info("Email found: "+email);
		return user;
	}
}
