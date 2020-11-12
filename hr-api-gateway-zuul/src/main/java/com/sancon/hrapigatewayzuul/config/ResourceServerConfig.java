package com.sancon.hrapigatewayzuul.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Autowired
	private JwtTokenStore tokenStore;
	
	private static final String [] PUBLIC = {"/hr-oauth/oauth/token"}; // rotas publicas
	private static final String [] OPERATOR = {"/hr-worker/**"}; // rotas para operadores
	private static final String [] ADMIN = {"/hr-payroll/**",
											"/hr-user/**",
											"/actuator/**",
											"/hr-worker/actuator/**",
											"/hr-oauth/actuator/**"}; // rotas para operadores
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll()
		.antMatchers(HttpMethod.GET,OPERATOR).hasAnyRole("OPERATOR","ADMIN")
		.antMatchers(ADMIN).hasAnyRole("ADMIN")
		.anyRequest().authenticated();
		http.cors().configurationSource(configurationSource());
	}
	
	
	@Bean
	public CorsConfigurationSource configurationSource() {
		CorsConfiguration c = new CorsConfiguration();
		c.setAllowedOrigins(Arrays.asList("*"));
		c.setAllowedMethods(Arrays.asList("POST","GET","DELETE","UPDATE","PATCH"));
		c.setAllowCredentials(true);
		c.setAllowedHeaders(Arrays.asList("Authorization","Content-type"));
		UrlBasedCorsConfigurationSource url = new UrlBasedCorsConfigurationSource();
		url.registerCorsConfiguration("/**", c);
		return url;
	}

	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(configurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	
}
