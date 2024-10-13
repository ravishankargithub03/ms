package com.demo.user_server.exchange.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CompanyResponse {

	private Long id;
	
	private String name;

	private String website;

	private String foundedDate;

	private String industry;

	private String ceoName;

	private String description;

	private BigDecimal revenue;
}
