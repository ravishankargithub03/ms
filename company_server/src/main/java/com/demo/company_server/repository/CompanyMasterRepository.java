package com.demo.company_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.company_server.dao.model.CompanyMaster;

public interface CompanyMasterRepository extends JpaRepository<CompanyMaster, Long> {

	@Query("SELECT cm FROM CompanyMaster cm WHERE cm.status = :status")
	List<CompanyMaster> findByStatus(@Param("status") Integer status);

	@Query("SELECT cm FROM CompanyMaster cm WHERE cm.id IN :ids AND cm.status = :status")
	List<CompanyMaster> findByIdsAndStatus(@Param("ids") List<Long> ids, @Param("status") Integer status);

}
