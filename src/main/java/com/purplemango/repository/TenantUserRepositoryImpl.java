package com.purplemango.repository;

import com.purplemango.aop.operations.BeforeTenantMongoOperation;
import com.purplemango.config.MultiTenantMongoDBFactory;
import com.purplemango.model.AddTenantUser;
import com.purplemango.model.Tenant;
import com.purplemango.model.TenantUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@BeforeTenantMongoOperation
public class TenantUserRepositoryImpl extends MongoBaseRepository<TenantUser> implements TenantUserRepository {

    public static final String COLLECTION_NAME = "users";
    public static final String DATABASE_NAME = "gms-tenants";
    private final MongoTemplate mongoTemplate;

    public TenantUserRepositoryImpl(MongoTemplate mongoTemplate) {
        super.setDatabaseName(DATABASE_NAME);
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Collection<TenantUser> findAllUserByTenant(String tenantId) {
        String databaseName = String.format("%s-%s", this.getTargetName(), tenantId);
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        Query query = new Query(Criteria.where("tenant").is(tenantId));
        return mongoTemplate.find(query, TenantUser.class, COLLECTION_NAME);
    }

    @Override
    public Page<TenantUser> findAll(String tenant, Pageable pageable) {
        String databaseName = String.format("%s-%s", this.getTargetName(), tenant);
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        Query query = new Query().with(pageable).with(pageable.getSort());
        List<TenantUser> filteredTenantUsers = mongoTemplate.find(query, TenantUser.class, COLLECTION_NAME);
        return PageableExecutionUtils.getPage( filteredTenantUsers, pageable, () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), TenantUser.class));
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
