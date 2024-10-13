package com.demo.company_server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.demo.company_server.exchange.service.UserServerExchange;

@Configuration
public class WebConfiguration {

	@Bean
	WebClient userWebClient() {
		return WebClient.builder().baseUrl("http://localhost:9091").build();
	}

	@Bean
	UserServerExchange userServerExchange() {
		HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
				.builderFor(WebClientAdapter.create(userWebClient())).build();
		return httpServiceProxyFactory.createClient(UserServerExchange.class);
	}
}
