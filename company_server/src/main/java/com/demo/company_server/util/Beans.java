package com.demo.company_server.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class Beans {

	@Bean
	public SecureRandom secureRandom() throws NoSuchAlgorithmException {
		return SecureRandom.getInstanceStrong();
	}
}
