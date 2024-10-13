package com.demo.company_server.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.company_server.dao.model.CompanyMaster;
import com.demo.company_server.exchange.model.UserResponse;
import com.demo.company_server.exchange.service.UserServerExchange;
import com.demo.company_server.repository.CompanyMasterRepository;
import com.demo.company_server.request.CompanyRequest;
import com.demo.company_server.response.CompanyResponse;
import com.demo.company_server.response.CompanyWithUserResponse;
import com.demo.company_server.response.Response;
import com.demo.company_server.service.inter_face.CompanyServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyService implements CompanyServiceInterface{
	
	private CompanyMasterRepository companyMasterRepository;
	
	private UserServerExchange userServerExchange;
	
//	================================================================
	
	@Override
	public List<CompanyResponse> getAllCompanies() {
		List<CompanyMaster> companies = null;
		List<CompanyResponse> companiesResponse = null;
		
		companies = companyMasterRepository.findByStatus(1);
		
		if(companies!=null && !companies.isEmpty())
		{
			companiesResponse = new ArrayList<>();
			
			for (CompanyMaster entity : companies) {
				CompanyResponse response = new CompanyResponse(entity.getId(), entity.getName(), entity.getWebsite(),entity.getFoundedDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
						entity.getIndustry(), entity.getCeoName(), entity.getDescription(), entity.getRevenue());
				companiesResponse.add(response);
			}
		}
				
		return companiesResponse; // Returns a 200 OK response with the data
	}

//	=======================================================================
	
	@Override
	public List<CompanyResponse> getCompanyById(List<Long> id, Integer status) {
		List<CompanyMaster> companies = null;
		List<CompanyResponse> companiesResponse = null;
		
		if (id == null) {
	        throw new IllegalArgumentException("ID must not be null");
	    }
		
		companies = companyMasterRepository.findByIdsAndStatus(id, status);
		
		if (companies != null && !companies.isEmpty()) {
			companiesResponse = new ArrayList<>();
			for (CompanyMaster entity : companies) {
				CompanyResponse companyResponse = new CompanyResponse(entity.getId(), entity.getName(), entity.getWebsite(), entity.getFoundedDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
						entity.getIndustry(), entity.getCeoName(), entity.getDescription(), entity.getRevenue());
				companiesResponse.add(companyResponse);
			}
		}
			
		return companiesResponse;
	}

//	=========================================================================
	
	@Override
	public List<CompanyMaster> saveCompany(List<CompanyRequest> companyRequest) {
		List<CompanyMaster> companyMastersListResponse = null;
		List<CompanyMaster> companyMastersList = new ArrayList<>();
		
		for (CompanyRequest request : companyRequest) {
			if (request != null) {
				CompanyMaster entity = new CompanyMaster();
				
				BeanUtils.copyProperties(request, entity);

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
				LocalDateTime localDateTime = LocalDateTime.parse(request.getFoundedDate(), formatter);
				entity.setStatus(1);
				entity.setFoundedDate(localDateTime);
				entity.setRevenue(new BigDecimal(request.getRevenue()));
				companyMastersList.add(entity);
			}
		}
		
		if (companyMastersList != null && !companyMastersList.isEmpty()) {
			companyMastersListResponse = companyMasterRepository.saveAll(companyMastersList);
		}
		
		return companyMastersListResponse; // Save the entity to the database
	}

//	================================================================
	
	@Override
	public List<CompanyWithUserResponse> getCompanyWithUserByCompanyId(List<Long> ids, Integer status) throws JsonProcessingException, IOException {
		List<CompanyMaster> companies = null;
		List<CompanyWithUserResponse> companiesResponse = null;
		List<UserResponse> usersResponses = null;
		
		companies = companyMasterRepository.findByIdsAndStatus(ids, status);
		
		if (companies != null && !companies.isEmpty()) {
			companiesResponse = new ArrayList<>();
			
			ObjectMapper objectMapper = new ObjectMapper();
			ResponseEntity<Response> users = userServerExchange.getUserByCompanyIds(ids, 1);
			usersResponses = convertJsonToUserResponse(objectMapper.writeValueAsString(users.getBody().getResponsePayload()));
			
			for (CompanyMaster entity : companies) {
				List<UserResponse> userResp = null;
				if (usersResponses != null)
					userResp = usersResponses.stream().filter(respo -> respo.getCompanyId().equals(entity.getId())).collect(Collectors.toList());
				
				CompanyWithUserResponse companyResponse = new CompanyWithUserResponse(entity.getId(), entity.getName(), entity.getWebsite(), entity.getFoundedDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
						entity.getIndustry(), entity.getCeoName(), entity.getDescription(), entity.getRevenue(), userResp);
				companiesResponse.add(companyResponse);
			}
		}
			
		return companiesResponse;
	}

//	==================================================================
	
	private List<UserResponse> convertJsonToUserResponse(String json) throws IOException {
	    ObjectMapper objectMapper = new ObjectMapper();
	    return objectMapper.readValue(json, new TypeReference<List<UserResponse>>() {});
	}
			
}


