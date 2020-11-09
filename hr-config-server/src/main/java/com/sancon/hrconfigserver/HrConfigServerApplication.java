package com.sancon.hrconfigserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableConfigServer
@SpringBootApplication
public class HrConfigServerApplication implements CommandLineRunner{

	@Autowired
	private Environment env;
	
	public static void main(String[] args) {
		SpringApplication.run(HrConfigServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(env.getProperty("spring.cloud.config.server.git.username"));
		log.info(env.getProperty("spring.cloud.config.server.git.password"));
	}

}
