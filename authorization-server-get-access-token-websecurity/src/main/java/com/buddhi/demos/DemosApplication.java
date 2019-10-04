package com.buddhi.demos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAuthorizationServer
@RestController
public class DemosApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemosApplication.class, args);
	}

	@RequestMapping("/account")
	public Account findAccount() {
		return new Account(1, 1, "123456789", 1234);
	}
}
