package com.purplemango.service;

import com.purplemango.exception.TenantDuplicateException;
import com.purplemango.model.AddTenant;
import com.purplemango.model.Tenant;
import com.purplemango.model.UpdateTenant;
import com.purplemango.repository.TenantRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TenantServiceImpl implements TenantService {
    TenantRepository tenantRepository;

    @Autowired
    public TenantServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Collection<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    @Override
    public Page<Tenant> getAllTenants(Pageable pageable) {
        return tenantRepository.findAll(pageable);
    }

    @Override
    public Tenant getTenantById(final String tenantId) {
        return tenantRepository.findById(tenantId);
    }

    @Override
    public Tenant createTenant(final AddTenant entity) {
        Tenant tenant = tenantRepository.findByCompanyNameAndCompanyCode(entity.companyName(), entity.companyCode());
        if (tenant != null) {
            throw new TenantDuplicateException("Tenant already exists");
        }

        return tenantRepository.save(Tenant.build(entity));
    }

    @Override
    public Tenant createOrUpdateTenant(UpdateTenant tenant) {
        return tenantRepository.createOrUpdateTenant(Tenant.upsert(tenant));
    }
}
