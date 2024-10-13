package com.demo.user_server.response;

import lombok.Data;

@Data
public class UserResponse {

	private Long id;

	private String firstName;

	private String secondName;

	private String dateOfBirth;

	private String gender;
	
	private Long companyId;

}
