package com.demo.company_server.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.company_server.common.Constants;
import com.demo.company_server.common.URLConstants;
import com.demo.company_server.dao.model.CompanyMaster;
import com.demo.company_server.request.CompanyRequest;
import com.demo.company_server.response.CompanyResponse;
import com.demo.company_server.response.CompanyWithUserResponse;
import com.demo.company_server.response.Response;
import com.demo.company_server.service.inter_face.CompanyServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Company Module")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(URLConstants.Company.BASE_API)
@AllArgsConstructor
public class CompanyController extends BaseController{

	private CompanyServiceInterface companyServiceInterface;

//	========================== Get All Company ============================
	
	@Operation(summary = "Get All Company", 
	           description = "This service is used for fetching all company based on their status.")
	@ApiResponses({
	    @ApiResponse(responseCode = Constants.OK+"", 
	                 description = "Company fetched successfully!", 
	                 content = @Content(schema = @Schema(implementation = Response.class))),
	    @ApiResponse(responseCode = Constants.NO_CONTENT+"", 
	                 description = "No Company found!", 
	                 content = @Content(schema = @Schema(implementation = Response.class)))
	})
	@GetMapping(URLConstants.Company.GET_ALL_COMPANY)
	public ResponseEntity<Response> getAllCompanies() {
		Response response = null;
		List<CompanyResponse> companies = null;
		
		companies = companyServiceInterface.getAllCompanies();
		
		if (companies != null && !companies.isEmpty()) {
			response = new Response(Constants.OK, "Company fetched successfully!", companies);
		} else {
			response = new Response(Constants.NO_CONTENT, "No Company found!", null);
		}
			
		return getOKResponseEntity(response); // Returns a 200 OK response with the data
	}
	
//	========================== Get Single And Multiple Company ============================

	@Operation(summary = "Get Single And Multiple Company", 
	           description = "This service is used for fetching single and multiple company based on company id and their status.")
	@ApiResponses({
	    @ApiResponse(responseCode = Constants.OK+"", 
	                 description = "Company fetched successfully!", 
	                 content = @Content(schema = @Schema(implementation = Response.class))),
	    @ApiResponse(responseCode = Constants.NO_CONTENT+"", 
	                 description = "No Company found!", 
	                 content = @Content(schema = @Schema(implementation = Response.class)))
	})
	@GetMapping(URLConstants.Company.GET_SINGLE_AND_MULTIPAL_COMPANY)
	public ResponseEntity<Response> getCompanyById(@PathVariable("companyId") List<Long> companyId, @PathVariable("status") Integer status) {
		Response response = null;
		List<CompanyResponse> companies = null;
		
		companies = companyServiceInterface.getCompanyById(companyId, status);
		
		if (companies != null && !companies.isEmpty()) {
			response = new Response(Constants.OK, "Company getting successfully!", companies);
		} else {
			response = new Response(Constants.NO_CONTENT, "No Company found!", null);
		}
		
		return getOKResponseEntity(response); // Returns a 200 OK response with the data
	}
	
//	========================== Save Company ============================
	
	@Operation(summary = "Save Company", 
	           description = "This service is used for saving company.")
	@ApiResponses({
	    @ApiResponse(responseCode = Constants.OK+"", 
	                 description = "Company saved successfully!", 
	                 content = @Content(schema = @Schema(implementation = Response.class))),
	    @ApiResponse(responseCode = Constants.INVALID_INPUT+"", 
	    			 description = "Invalid input provided!", 
	                 content = @Content(schema = @Schema(implementation = Response.class))),
	    @ApiResponse(responseCode = Constants.DATA_SAVING_ERROR+"", 
        			 description = "Data saving error!", 
        			 content = @Content(schema = @Schema(implementation = Response.class))),
	    @ApiResponse(responseCode = Constants.INTERNAL_SERVER_ERROR+"", 
        			 description = "Internal Server Error!", 
        			 content = @Content(schema = @Schema(implementation = Response.class)))
	})
	@PostMapping(URLConstants.Company.SAVE_COMPANY)
	public ResponseEntity<Response> saveCompany(@Validated @RequestBody List<CompanyRequest> companyRequest) {
		Response response = null;
		List<CompanyMaster> companyList = null;
		
		if (companyRequest != null) {
			companyList = companyServiceInterface.saveCompany(companyRequest);

			if (companyList != null && !companyList.isEmpty()) {
				response = new Response(Constants.OK, "Company saved successfully!", companyList);
			} else {
				response = new Response(Constants.DATA_SAVING_ERROR, "Data saving error!", null);
			}
		} else {
			response = new Response(Constants.INVALID_INPUT, "Invalid input provided!", null);
		}
		
		return getOKResponseEntity(response); // Returns a 200 OK response with the data
	}
	
//	========================== Get Company With User ============================
	
	@Operation(summary = "Get Company With User", 
	           description = "This service is used for fetching single and multipal company with user based on company id and their status.")
	@ApiResponses({
	    @ApiResponse(responseCode = Constants.OK+"", 
	                 description = "Company with user fetched successfully!", 
	                 content = @Content(schema = @Schema(implementation = Response.class))),
	    @ApiResponse(responseCode = Constants.NO_CONTENT+"", 
	                 description = "No company found!", 
	                 content = @Content(schema = @Schema(implementation = Response.class)))
	})
	@GetMapping(URLConstants.Company.GET_SINGLE_AND_MULTIPAL_COMPANY_WITH_USER)
	public ResponseEntity<Response> getCompanyWithUserByCompanyId(@PathVariable("companyId") List<Long> companyId, 
			@PathVariable("status") Integer status) throws JsonProcessingException, IOException {
		Response response = null;
		List<CompanyWithUserResponse> companies = null;
		
		if (companyId == null || companyId.isEmpty())
			return getOKResponseEntity(new Response(422, "Company ID must not be null!", null));
        
		companies = companyServiceInterface.getCompanyWithUserByCompanyId(companyId, status);
		
		if (companies != null && !companies.isEmpty()) {
			response = new Response(Constants.OK, "Company with user fetched successfully!", companies);
		} else {
			response = new Response(Constants.NO_CONTENT, "No company found!", null);
		}
		
		return getOKResponseEntity(response); // Returns a 200 OK response with the data
	}
	
}
