package com.demo.company_server.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
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
