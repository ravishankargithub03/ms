package com.demo.user_server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.demo.user_server.exchange.service.CompanyServerExchange;

@Configuration
public class WebConfiguration {

	@Bean
	WebClient companyWebClient() {
		return WebClient.builder().baseUrl("http://localhost:9092").build();
	}

	@Bean
	CompanyServerExchange companyServerExchange() {
		HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
				.builderFor(WebClientAdapter.create(companyWebClient())).build();
		return httpServiceProxyFactory.createClient(CompanyServerExchange.class);
	}
}
