package com.demo.user_server.dao.model;

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

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_MAST")
@GenericGenerator(name = "random_id", strategy = "com.rjtech.enterprise.util.RandomIdGenerator")
public class UserMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UM_ID")
	private Long id;
	
	@Column(name = "UM_FIRST_NAME")
	private String firstName;
	
	@Column(name = "UM_SECOND_NAME")
	private String secondName;
	
	@Column(name = "UM_DATE_OF_BIRTH")
	private LocalDateTime dateOfBirth;
	
	@Column(name = "UM_GENDER")
	private String gender;
	
	@Column(name = "UM_COMPANY_Id")
	private Long companyId;
	
	@Column(name = "UM_STATUS")
	private Integer status;
	
}
