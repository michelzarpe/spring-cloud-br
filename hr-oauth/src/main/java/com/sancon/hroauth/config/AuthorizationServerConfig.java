package com.sancon.hroauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import lombok.extern.slf4j.Slf4j;

//configuração do servidor de autorização


@Slf4j
@RefreshScope
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private Environment env;
	
	@Autowired
	private BCryptPasswordEncoder bcp;
	
	@Autowired
	private JwtAccessTokenConverter jwtAc;
	
	@Autowired
	private JwtTokenStore  jwtTokenStore;
	
	@Autowired
	private AuthenticationManager authManager;
	
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		    log.info("Chave: "+env.getProperty("oauth.client.name"));
		    log.info("Chave: "+env.getProperty("oauth.client.secret"));
		    
			clients.inMemory()
			.withClient(env.getProperty("oauth.client.name"))
			.secret(bcp.encode(env.getProperty("oauth.client.secret")))
			.scopes("read","write")
			.authorizedGrantTypes("password")
			.accessTokenValiditySeconds(86400);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.authenticationManager(authManager)
			.tokenStore(jwtTokenStore)
			.accessTokenConverter(jwtAc);
	}
	
	
	
	
	
}
