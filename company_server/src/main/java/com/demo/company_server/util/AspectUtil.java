package com.demo.company_server.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class AspectUtil {

	@Before("execution(* com.demo.company_server..*(..)) && !within(*..security..*)")
	public void logMethodStart(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().toShortString();
		log.info("{} :START of the method", methodName);
	}
	
	@After("execution(* com.demo.company_server..*(..)) && !within(*..security..*)")
	public void logMethodEnd(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().toShortString();
		log.info("{} :END of the method", methodName);
	}
	
}
