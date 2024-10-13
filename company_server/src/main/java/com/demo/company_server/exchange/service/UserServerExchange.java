package com.demo.company_server.exchange.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.demo.company_server.response.Response;
import com.demo.company_server.common.*;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@HttpExchange(URLConstants.User.BASE_API)
public interface UserServerExchange {

	@CircuitBreaker(name = "userServiceCircuitBreaker", fallbackMethod = "fallbackGetUserByCompanyIds")
	@GetExchange(URLConstants.User.GET_USER_LIST_BY_SINGLE_AND_MULTIPAL_COMPAY_ID)
	public ResponseEntity<Response> getUserByCompanyIds(@PathVariable("companyIds") List<Long> companyIds, @PathVariable("userStatus") Integer userStatus);
	
	default ResponseEntity<Response> fallbackGetUserByCompanyIds(List<Long> companyIds, Integer status, Throwable e) {
	        // Log the error if needed
	        // You might want to use a logging framework like SLF4J or Log4j
	        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
	                             .body(new Response(404, "User service is currently unavailable.", null));
	    }
}
