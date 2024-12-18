package com.purplemango.service;

import com.purplemango.model.AddTenantUser;
import com.purplemango.model.Tenant;
import com.purplemango.model.TenantUser;
import com.purplemango.repository.TenantUserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TenantUserServiceImpl implements TenantUserService {

    TenantUserRepository tenantUserRepository;
    TenantService tenantService;

    public TenantUserServiceImpl(TenantUserRepository tenantUserRepository, TenantService tenantService) {
        this.tenantUserRepository = tenantUserRepository;
        this.tenantService = tenantService;
    }

    @Override
    public Collection<TenantUser> getAllTenantUsers(String tenantId) {
        Tenant tenant = tenantService.getTenantById(tenantId);
        return tenantUserRepository.findAllUserByTenant(tenant.companyCode());
    }

    @Override
    public Page<TenantUser> getAllTenantUsers(String tenantId,Pageable pageable) {
        Tenant tenant = tenantService.getTenantById(tenantId);
        return tenantUserRepository.findAll(tenant.companyCode(), pageable);
    }

    @Override
    public List<TenantUser> getTenantUsersByTenantId(String tenantId) {
        return List.of();
    }

    @Override
    public TenantUser getUserById(String tenantId) {
        return null;
    }

    @Override
    public TenantUser createTenantUser(AddTenantUser entity) {
        Tenant tenant = tenantService.getTenantById(entity.tenantId());
        return tenantUserRepository.save(TenantUser.build(entity, tenant.companyCode()));
    }
}
