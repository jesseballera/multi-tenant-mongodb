package com.purplemango.repository;

import com.purplemango.model.AddTenant;
import com.purplemango.model.Tenant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.UUID;

public interface TenantRepository {
    Tenant findByCompanyNameAndCompanyCode(String companyName, String companyCode);
    Collection<Tenant> findAll();
    Page<Tenant> findAll(Pageable pageable);
    Tenant findById(final String tenantId);
    Tenant  save(Tenant tenant);
    Tenant createOrUpdateTenant(Tenant tenant);
}
