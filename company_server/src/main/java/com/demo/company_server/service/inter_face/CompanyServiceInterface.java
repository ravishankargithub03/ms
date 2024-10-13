package com.demo.company_server.service.inter_face;

import java.io.IOException;
import java.util.List;

import com.demo.company_server.dao.model.CompanyMaster;
import com.demo.company_server.request.CompanyRequest;
import com.demo.company_server.response.CompanyResponse;
import com.demo.company_server.response.CompanyWithUserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CompanyServiceInterface {

	List<CompanyResponse> getAllCompanies();

	List<CompanyResponse> getCompanyById(List<Long> companyId, Integer status);

	List<CompanyMaster> saveCompany(List<CompanyRequest> companyRequest);

	List<CompanyWithUserResponse> getCompanyWithUserByCompanyId(List<Long> companyId, Integer status) throws JsonProcessingException, IOException;

}
