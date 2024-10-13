package com.demo.company_server.response;

import java.math.BigDecimal;
import java.util.List;

import com.demo.company_server.exchange.model.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyWithUserResponse {

	private Long id;
	
	private String name;

	private String website;

	private String foundedDate;

	private String industry;

	private String ceoName;

	private String description;

	private BigDecimal revenue;
	
	private List<UserResponse> user;
	
}
