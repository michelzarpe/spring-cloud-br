package com.sancon.hroauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RefreshScope
@Configuration
public class AppConfig {

	@Autowired
	private Environment env;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		log.info("Chave: "+env.getProperty("jwt.secret"));
		//tokenConverter.setSigningKey("MY-SECRET-KEY");
		tokenConverter.setSigningKey(env.getProperty("jwt.secret"));
		return tokenConverter;
	}
	
	@Bean
	public JwtTokenStore jwtTokenStore () {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	
}
