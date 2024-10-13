package com.demo.user_server.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.user_server.dao.model.UserMaster;
import com.demo.user_server.exchange.model.CompanyResponse;
import com.demo.user_server.exchange.service.CompanyServerExchange;
import com.demo.user_server.repository.UserMasterRepository;
import com.demo.user_server.request.UserRequest;
import com.demo.user_server.response.Response;
import com.demo.user_server.response.UserResponse;
import com.demo.user_server.response.UserWithCompanyResponse;
import com.demo.user_server.service.inter_face.UserServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.micrometer.observation.ObservationRegistry;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface{

	private CompanyServerExchange companyServerExchange;

	private ObservationRegistry observationRegistry;
	
	private UserMasterRepository userMasterRepository;

	List<UserMaster> users = new ArrayList<>();

//	======================================================
	
	@Override
	public List<UserWithCompanyResponse> getAllUsers(Integer status) throws JsonProcessingException, IOException {
		List<UserWithCompanyResponse> userResponses = null;
		List<UserMaster> userEntities = null;
		List<CompanyResponse> company = null;
		
        userEntities = userMasterRepository.findAllByStatus(status);
        
		if (userEntities != null && !userEntities.isEmpty()) {
			userResponses = new ArrayList<>();
			for (UserMaster entity : userEntities) {
				UserWithCompanyResponse userResponse = new UserWithCompanyResponse();
				BeanUtils.copyProperties(entity, userResponse);
				userResponse.setDateOfBirth(entity.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
				
				ObjectMapper objectMapper = new ObjectMapper();
				
				ResponseEntity<Response> companyResponse = companyServerExchange.getCompanyById(entity.getCompanyId(), 1);
				if (companyResponse != null)
					company = convertJsonToCompanyResponse(objectMapper.writeValueAsString(companyResponse.getBody().getResponsePayload()));
				
				if (company != null) {
				    company.stream().filter(resp -> entity.getCompanyId().equals(resp.getId())).findFirst().ifPresent(userResponse::setCompany);
				}
				
				userResponses.add(userResponse);
			}
		}
			
		return userResponses;
	}

//	==========================================================
	
	@Override
	public List<UserResponse> getUserById(List<Long> ids) throws IOException {
		List<UserResponse> userResponse = null;
		List<UserMaster> userMaster = null;
		
		userMaster = userMasterRepository.findByIdAndStatus(ids, 1);
		
		if (userMaster != null && !userMaster.isEmpty()) {
			userResponse = new ArrayList<>();
			
			for(UserMaster entity : userMaster) {
				UserResponse user = new UserResponse();
                BeanUtils.copyProperties(entity, user);
                user.setDateOfBirth(entity.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                userResponse.add(user);
			}
		}
		
		return userResponse;
	}
	
//	==========================================================
	
	@Override
	public List<UserWithCompanyResponse> getUserWithCompanyByUserId(List<Long> userIds, Integer status) throws IOException {
		List<UserWithCompanyResponse> userResponse = null;
		List<UserMaster> userMaster = null;
		List<CompanyResponse> objectToCompanyResponse = null;
		
		userMaster = userMasterRepository.findByIdAndStatus(userIds, status);
		
		if (userMaster != null && !userMaster.isEmpty()) {
			userResponse = new ArrayList<UserWithCompanyResponse>();
			for (UserMaster entity : userMaster) {
				UserWithCompanyResponse response = new UserWithCompanyResponse();
				BeanUtils.copyProperties(entity, response);
				response.setDateOfBirth(entity.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
				
				ObjectMapper objectMapper = new ObjectMapper();
				
				ResponseEntity<Response> companyResponseList = companyServerExchange.getCompanyById(entity.getCompanyId(), status);
				if (companyResponseList != null)
					objectToCompanyResponse = convertJsonToCompanyResponse(objectMapper.writeValueAsString(companyResponseList.getBody().getResponsePayload()));
				
				if(objectToCompanyResponse!=null) {
					for(CompanyResponse companyResponse : objectToCompanyResponse) {
						response.setCompany(companyResponse);
					}
				}
				
				userResponse.add(response);
			}
		}
		
		return userResponse;
	}

//	==========================================================
	
	@Override
	public List<Map<String, Object>> saveUser(List<UserRequest> userRequest) {
		List<Map<String, Object>> mapResponseList = new ArrayList<>();
		List<UserMaster> entityList = new ArrayList<>();
		Map<String, Object> mapResponse = null;
		
		for(UserRequest request : userRequest) {
			if (request != null) {
				UserMaster entity = new UserMaster();
				BeanUtils.copyProperties(request, entity);
		
				LocalDateTime formattedDOB = LocalDateTime.parse(request.getDateOfBirth(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
				entity.setStatus(1);
				entity.setDateOfBirth(formattedDOB);
				
				ResponseEntity<Response> companyResponse = companyServerExchange.getCompanyById(request.getCompanyId(), 1);
				if (companyResponse.getBody().getResponsePayload() == null) {
					mapResponse = new HashMap<>();
					mapResponse.put("message", "Company not found with "+request.getCompanyId()+" id!");
					mapResponseList.add(mapResponse);
					return mapResponseList;
				} else {
					if (companyResponse.getBody().getResponsePayload() != null) {
						entity.setCompanyId(request.getCompanyId());
						entityList.add(entity);
					}
				}
			}
		}
		
		List<UserMaster> isSaved = userMasterRepository.saveAll(entityList);
		
		if(isSaved!=null && !isSaved.isEmpty()) {
			mapResponse = new HashMap<>();
			mapResponse.put("object", isSaved);
			mapResponseList.add(mapResponse);
		}
		
 		return mapResponseList; // Save the entity to the database
	}
	
//	===========================================================
	
	private List<CompanyResponse> convertJsonToCompanyResponse(String json) throws IOException {
	    ObjectMapper objectMapper = new ObjectMapper();
	    return objectMapper.readValue(json, new TypeReference<List<CompanyResponse>>() {});
	}
	
//	===========================================================
	
	@Override
	public List<UserResponse> getUserByCompanyIds(List<Long> companyIds, Integer status) {
		List<UserResponse> userResponse = null;
		List<UserMaster> userMaster = null;
		
		userMaster = userMasterRepository.findByCompaniesIdsAndStatus(companyIds, status);
		
		if (userMaster != null && !userMaster.isEmpty()) {
			userResponse = new ArrayList<>();
			
			for(UserMaster entity : userMaster) {
				UserResponse user = new UserResponse();
                BeanUtils.copyProperties(entity, user);
                user.setDateOfBirth(entity.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                userResponse.add(user);
			}
		}
		
		return userResponse;
	}
}
