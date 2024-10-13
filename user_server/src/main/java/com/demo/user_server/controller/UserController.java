package com.demo.user_server.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.user_server.common.Constants;
import com.demo.user_server.common.URLConstants;
import com.demo.user_server.request.UserRequest;
import com.demo.user_server.response.Response;
import com.demo.user_server.response.UserResponse;
import com.demo.user_server.response.UserWithCompanyResponse;
import com.demo.user_server.service.inter_face.UserServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(URLConstants.User.BASE_API)
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class UserController extends BaseController{
	
	private UserServiceInterface userServiceInterface;
	
//	========================================================
	
	@Operation(summary = "Get All User With Company", 
	           description = "This service is used for fetching all user with company based on their status.")
	@ApiResponses({
	    @ApiResponse(responseCode = Constants.OK+"", 
	                 description = Constants.ResponseMessages.FETCHED_MESSAGE, 
	                 content = @Content(schema = @Schema(implementation = Response.class))),
	    @ApiResponse(responseCode = Constants.NO_CONTENT+"", 
	                 description = Constants.ResponseMessages.DATA_NOT_AVAILABLE_MESSAGE, 
	                 content = @Content(schema = @Schema(implementation = Response.class)))
	})
	@GetMapping(URLConstants.User.GET_ALL)
	public ResponseEntity<Response> getAllUsers(@PathVariable("status") Integer status) throws JsonProcessingException, IOException {
		Response response = null;
		List<UserWithCompanyResponse> usersResponse = null;
		
		usersResponse = userServiceInterface.getAllUsers(status);
		
		if (usersResponse != null) {
			response = new Response(Constants.OK, Constants.ResponseMessages.FETCHED_MESSAGE, usersResponse);
		} else {
			response = new Response(Constants.NO_CONTENT, Constants.ResponseMessages.DATA_NOT_AVAILABLE_MESSAGE, null);
		}
		
        return getOKResponseEntity(response); // Returns a 200 OK response with the data
    }
	
//	========================================================
	
	@Operation(summary = "Get Single And Multiple User", 
	           description = "This service is used for fetching single and multiple based on their status.")
	@ApiResponses({
	    @ApiResponse(responseCode = Constants.OK+"", 
	                 description = Constants.ResponseMessages.FETCHED_MESSAGE, 
	                 content = @Content(schema = @Schema(implementation = Response.class))),
	    @ApiResponse(responseCode = Constants.NO_CONTENT+"", 
	                 description = "No Company found!", 
	                 content = @Content(schema = @Schema(implementation = Response.class)))
	})
	@GetMapping(URLConstants.User.GET_SINGLE_AND_MULTIPAL_USER)
	public ResponseEntity<Response> getUserById(@PathVariable("userIds") List<Long> userIds) throws IOException {
		Response response = null;
		List<UserResponse> userResponse = null;
		
		userResponse = userServiceInterface.getUserById(userIds);
		
		if (userResponse != null) {
			response = new Response(Constants.OK, Constants.ResponseMessages.FETCHED_MESSAGE, userResponse);
		} else {
			response = new Response(Constants.NO_CONTENT, Constants.ResponseMessages.DATA_NOT_AVAILABLE_MESSAGE, null);
		}
		
		return getOKResponseEntity(response); // Returns a 200 OK response with the data
    }
	
//	========================================================
	
	@Operation(summary = "Get User With Company By User Id", 
	           description = "This service is used for fetching user with company based on user id.")
	@ApiResponses({
	    @ApiResponse(responseCode = Constants.OK+"", 
	                 description = Constants.ResponseMessages.FETCHED_MESSAGE, 
	                 content = @Content(schema = @Schema(implementation = Response.class))),
	    @ApiResponse(responseCode = Constants.NO_CONTENT+"", 
	                 description = Constants.ResponseMessages.DATA_NOT_AVAILABLE_MESSAGE, 
	                 content = @Content(schema = @Schema(implementation = Response.class)))
	})
	@GetMapping(URLConstants.User.GET_USER_WITH_COMPANY)
	public ResponseEntity<Response> getUserWithCompanyByUserId(@PathVariable("userId") List<Long> userIds, @PathVariable("status") Integer status) throws IOException {
		Response response = null;
		List<UserWithCompanyResponse> userResponse = null;
		
		userResponse = userServiceInterface.getUserWithCompanyByUserId(userIds, status);
		
		if (userResponse != null) {
			response = new Response(Constants.OK, Constants.ResponseMessages.FETCHED_MESSAGE, userResponse);
		} else {
			response = new Response(Constants.NO_CONTENT, Constants.ResponseMessages.DATA_NOT_AVAILABLE_MESSAGE, null);
		}
		
		return getOKResponseEntity(response); // Returns a 200 OK response with the data
    }
	
//	========================================================
	
	@Operation(summary = "Save User", 
	           description = "This service is used for saving user.")
	@ApiResponses({
	    @ApiResponse(responseCode = Constants.OK+"", 
	                 description = Constants.ResponseMessages.SAVE_MESSAGE, 
	                 content = @Content(schema = @Schema(implementation = Response.class))),
	    @ApiResponse(responseCode = Constants.INVALID_INPUT+"", 
	    			 description = Constants.ResponseMessages.INVALID_DATA_MESSAGE, 
	                 content = @Content(schema = @Schema(implementation = Response.class))),
	    @ApiResponse(responseCode = Constants.DATA_SAVING_ERROR+"", 
     			 description = Constants.ResponseMessages.UNABLE_SAVE_MESSAGE, 
     			 content = @Content(schema = @Schema(implementation = Response.class))),
	    @ApiResponse(responseCode = Constants.INTERNAL_SERVER_ERROR+"", 
     			 description = Constants.ResponseMessages.SERVER_ERROR_MESSAGE, 
     			 content = @Content(schema = @Schema(implementation = Response.class)))
	})
	@PostMapping(URLConstants.User.SAVE)
	public ResponseEntity<Response> saveUser(@Validated @RequestBody List<UserRequest> userRequest) {
		Response response = null;
		List<Map<String, Object>> usersList = null;

		if (userRequest != null) {
			usersList = userServiceInterface.saveUser(userRequest);
			if (usersList != null && !usersList.isEmpty() && usersList.get(0).get("message") == null) {
				response = new Response(Constants.OK, Constants.ResponseMessages.SAVE_MESSAGE, usersList);
			} else {
				response = new Response(Constants.DATA_SAVING_ERROR, Constants.ResponseMessages.UNABLE_SAVE_MESSAGE, usersList.get(0).get("message"));
			}
		} else {
			response = new Response(Constants.INVALID_INPUT, Constants.ResponseMessages.INVALID_INPUT_MESSAGE, null);
		}
		
		return getOKResponseEntity(response); // Returns a 200 OK response with the data
	}
	
//	========================================================
	
	@Operation(summary = "Get User By Company Id And User Status", 
	           description = "This service is used for fetching all users based on company id and user status.")
	@ApiResponses({
	    @ApiResponse(responseCode = Constants.OK+"", 
	                 description = Constants.ResponseMessages.FETCHED_MESSAGE, 
	                 content = @Content(schema = @Schema(implementation = Response.class))),
	    @ApiResponse(responseCode = Constants.NO_CONTENT+"", 
	                 description = Constants.ResponseMessages.DATA_NOT_AVAILABLE_MESSAGE, 
	                 content = @Content(schema = @Schema(implementation = Response.class)))
	})
	@GetMapping(URLConstants.User.GET_USER_LIST_BY_SINGLE_AND_MULTIPAL_COMPAY_ID)
	public ResponseEntity<Response> getUserByCompanyIdsAndUserStatus(@PathVariable("companyIds") List<Long> companyIds, @PathVariable("userStatus") Integer userStatus) throws IOException {
		Response response = null;
		List<UserResponse> userResponse = null;
		
		userResponse = userServiceInterface.getUserByCompanyIds(companyIds, userStatus);
		
		if (userResponse != null) {
			response = new Response(Constants.OK, Constants.ResponseMessages.FETCHED_MESSAGE, userResponse);
		} else {
			response = new Response(Constants.NO_CONTENT, Constants.ResponseMessages.DATA_NOT_AVAILABLE_MESSAGE, null);
		}
		
		return getOKResponseEntity(response); // Returns a 200 OK response with the data
    }
	
}
