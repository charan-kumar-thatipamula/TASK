package com.kastech.repository;

import com.kastech.model.TenantSetup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantSetupRepository extends CrudRepository<TenantSetup, String> {
    List<TenantSetup> findByTenant(String tenant);
}