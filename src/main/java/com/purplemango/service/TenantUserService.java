package com.purplemango.service;

import com.purplemango.model.AddTenant;
import com.purplemango.model.AddTenantUser;
import com.purplemango.model.Tenant;
import com.purplemango.model.TenantUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface TenantUserService {

    Collection<TenantUser> getAllTenantUsers();
    Page <TenantUser> getAllTenantUsers(Pageable pageable);
    List<TenantUser> getTenantUsersByTenantId(final String tenantId);
    TenantUser getUserById(final String tenantId);
    TenantUser createTenantUser(final AddTenantUser tenant);
}
