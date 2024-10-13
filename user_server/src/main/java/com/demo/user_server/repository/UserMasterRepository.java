package com.demo.user_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.user_server.dao.model.UserMaster;

public interface UserMasterRepository extends JpaRepository<UserMaster, Long> {

	@Query("SELECT um FROM UserMaster um WHERE um.id IN :id AND um.status = :status")
	List<UserMaster> findByIdAndStatus(@Param("id") List<Long> id, @Param("status") Integer status);

	@Query("SELECT um FROM UserMaster um WHERE um.status = :status")
	List<UserMaster> findAllByStatus(@Param("status") Integer status);

	@Query("SELECT um FROM UserMaster um WHERE um.companyId IN :companyIds AND um.status = :status")
	List<UserMaster> findByCompaniesIdsAndStatus(@Param("companyIds") List<Long> companyIds, @Param("status") Integer status);

//	@Query("SELECT um FROM UserMaster um WHERE um.status = :status")
//	List<UserMaster> findAllByIdAndStatus(List<Long> ids, int i);

}
