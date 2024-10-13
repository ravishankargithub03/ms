package com.demo.company_server.dao.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COMPANY_MAST")
@GenericGenerator(name = "random_id", strategy = "com.rjtech.enterprise.util.RandomIdGenerator")
public class CompanyMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CM_ID")
	private Long id;
	
	@Column(name = "CM_NAME")
	private String name;
	
	@Column(name = "CM_WEBSITE")
	private String website;
	
	@Column(name = "CM_FOUNDED_DATE")
	private LocalDateTime foundedDate;
	
	@Column(name = "CM_INDUSTRY")
	private String industry;
	
	@Column(name = "CM_STATUS")
	private Integer status;
	
	@Column(name = "CM_CEO_NAME")
	private String ceoName;
	
	@Column(name = "CM_DESCRIPTION")
	private String description;
	
	@Column(name = "CM_REVENUE")
	private BigDecimal revenue;

}
