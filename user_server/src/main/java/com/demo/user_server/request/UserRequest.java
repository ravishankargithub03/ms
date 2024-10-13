package com.demo.user_server.request;

import java.io.Serializable;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest implements Serializable{

	private static final long serialVersionUID = 7929636028312624831L;

	private Long id;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String secondName;
	
	@NotBlank
	private String dateOfBirth;
	
	@NotNull
	private String gender;
	
	@NotNull
	private Long companyId;

}
