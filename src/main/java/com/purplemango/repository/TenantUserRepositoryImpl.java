package com.purplemango.repository;

import com.purplemango.aop.operations.BeforeTenantMongoOperation;
import com.purplemango.model.AddTenantUser;
import com.purplemango.model.Tenant;
import com.purplemango.model.TenantUser;
import org.springframework.stereotype.Repository;

@Repository
@BeforeTenantMongoOperation
public class TenantUserRepositoryImpl extends MongoBaseRepository<TenantUser> implements TenantUserRepository {

    public static final String COLLECTION_NAME = "users";
    public static final String DATABASE_NAME = "gms-tenants";

    public TenantUserRepositoryImpl() {
        super.setDatabaseName(DATABASE_NAME);
    }
    @Override
    public Tenant findByTenantId(String tenantId) {
        return null;
    }

    @Override
    public TenantUser findByTenantIdAndUserId(String tenantId, String userId) {
        return null;
    }

    @Override
    public TenantUser save(TenantUser entity) {
        return save(entity, COLLECTION_NAME);
    }
}
