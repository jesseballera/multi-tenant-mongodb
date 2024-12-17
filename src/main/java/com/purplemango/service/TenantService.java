package com.purplemango.service;

import com.purplemango.model.AddTenant;
import com.purplemango.model.Tenant;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.UUID;

public interface TenantService {

    Collection<Tenant> getAllTenants();
    Page <Tenant> getAllTenants(Pageable pageable);
    Tenant getTenantById(final String tenantId);
    Tenant createTenant(final AddTenant tenant);
}
