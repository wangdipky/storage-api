package com.vang.main.repositories;

import com.vang.main.entities.AuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: AuthInfoRepository
 */
@Repository
public interface AuthInfoRepository extends JpaRepository<AuthInfo, Integer> {

    @Query(value = "SELECT af.* FROM auth_info af WHERE af.deleted_date IS NULL AND af.active = 1 AND EMAIL = ?1", nativeQuery = true)
    AuthInfo findByEmail(String email);

    @Query(value = "SELECT COUNT(*) FROM auth_info af WHERE af.deleted_date IS NULL AND af.active = 1 AND EMAIL = ?1", nativeQuery = true)
    int countByEmail(String email);

    @Query(value = "SELECT COUNT(*) FROM auth_info af WHERE af.deleted_date IS NULL AND af.active = 1 AND COMPANY_CODE = ?1", nativeQuery = true)
    int countByCompanyCode(String companyCode);
}