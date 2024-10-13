package com.demo.user_server.response;

import com.demo.user_server.exchange.model.CompanyResponse;

import lombok.Data;

@Data
public class UserWithCompanyResponse {

	private Long id;
	
	private String firstName;
	
	private String secondName;
	
	private String dateOfBirth;
	
	private String gender;
	
	private CompanyResponse company;
}
