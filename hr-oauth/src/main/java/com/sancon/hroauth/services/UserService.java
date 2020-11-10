package com.sancon.hroauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sancon.hroauth.entities.User;
import com.sancon.hroauth.feignclients.UserFeignClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements UserDetailsService {
	
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userFeignClient.findByEmail(username).getBody();
		if(user ==null) {
			log.error("Email not found: "+username);
			throw new UsernameNotFoundException("Email not Found");
		}
		log.info("Email found: "+username);
		return user;
	}
}
