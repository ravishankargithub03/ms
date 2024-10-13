package com.demo.user_server.service.inter_face;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.demo.user_server.request.UserRequest;
import com.demo.user_server.response.UserResponse;
import com.demo.user_server.response.UserWithCompanyResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserServiceInterface {

	List<UserWithCompanyResponse> getAllUsers(Integer status) throws JsonProcessingException, IOException;

	List<UserResponse> getUserById(List<Long> userIds) throws IOException;

	List<UserWithCompanyResponse> getUserWithCompanyByUserId(List<Long> userIds, Integer status) throws IOException;

	List<Map<String, Object>> saveUser(List<UserRequest> userRequest);

	List<UserResponse> getUserByCompanyIds(List<Long> companyIds, Integer status);

	
}
