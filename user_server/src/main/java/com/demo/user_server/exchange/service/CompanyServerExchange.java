package com.demo.user_server.exchange.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.demo.user_server.response.Response;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@HttpExchange("/company")
public interface CompanyServerExchange {

	@CircuitBreaker(name = "companyServiceCircuitBreaker", fallbackMethod = "fallbackGetCompanyByCompanyIds")
	@GetExchange("/get/{id}/{status}")
	ResponseEntity<Response> getCompanyById(@PathVariable("id") Long id, @PathVariable Integer status);
	
	
	default ResponseEntity<Response> fallbackGetCompanyByCompanyIds(Long companyIds, Integer status, Throwable e) {
        // Log the error if needed
        // You might want to use a logging framework like SLF4J or Log4j
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                             .body(new Response(404, "Company service is currently unavailable.", null));
    }
}
