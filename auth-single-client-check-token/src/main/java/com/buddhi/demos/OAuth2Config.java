package com.buddhi.demos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
				.inMemory()
				.withClient("a")
				.secret(passwordEncoder().encode("a"))
				.authorities("ROLE_A","ROLE_B","ROLE_TRUSTED_CLIENT")
				.scopes("all")
				.authorizedGrantTypes("client_credentials")
				.and()
				.withClient("b")
				.secret(passwordEncoder().encode("b"))
				.authorities("ROLE_B")
				.scopes("all")
				.authorizedGrantTypes("client_credentials");
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		security.checkTokenAccess("denyAll()");//request: oauth/check_token response: {"timestamp":"2019-09-30T01:46:55.801+0000","status":403,"error":"Forbidden","message":"Forbidden","path":"/oauth/check_token"}
//		security.checkTokenAccess("permitAll()");//request: oauth/check_token response: {"scope":["all"],"active":true,"exp":1569851327,"authorities":["ROLE_A","ROLE_B"],"client_id":"a"}, can get token details with or without authentication
//		security.checkTokenAccess("isAnonymous()");//without any authentication can request oauth/check_token
		security.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");//the user need to have ROLE_TRUSTED_CLIENT authority to request oauth/check_token ex. user only user "a" can request, user "b" doesn't has this authority
//		security.checkTokenAccess("hasAuthority('ROLE_B')");//both user "a" and "b" can access
//		security.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')");
	}

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder(4);
	}
}
