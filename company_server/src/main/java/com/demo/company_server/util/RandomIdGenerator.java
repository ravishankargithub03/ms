package com.demo.company_server.util;

import java.io.Serializable;
import java.security.SecureRandom;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RandomIdGenerator implements IdentifierGenerator{

	private static final long serialVersionUID = 1L;
	private SecureRandom random;

	public Long generateRandomId() {
		long currentTimeMillis = System.currentTimeMillis();
		int randomNumber = random.nextInt(1000);
		return currentTimeMillis * 1000 + randomNumber;
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		return generateRandomId();
	}
}
