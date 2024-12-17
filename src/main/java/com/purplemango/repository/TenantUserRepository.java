package com.purplemango.repository;

import com.purplemango.aop.operations.BeforeTenantMongoOperation;
import com.purplemango.model.Tenant;
import com.purplemango.model.TenantUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface TenantUserRepository {
    Tenant findByTenantId(String tenantId);
    TenantUser findByTenantIdAndUserId(String tenantId, String userId);
    TenantUser save(TenantUser entity);
}
