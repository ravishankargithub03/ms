package com.demo.company_server.request;

import java.io.Serializable;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyRequest implements Serializable {

	private static final long serialVersionUID = 2510437797659574658L;

	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String website;

	@NotBlank
	private String foundedDate;

	@NotBlank
	private String industry;

	@NotBlank
	private String ceoName;

	@NotBlank
	private String description;

	@NotNull
	private Double revenue;
}
