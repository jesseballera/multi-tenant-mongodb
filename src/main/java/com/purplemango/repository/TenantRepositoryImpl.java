package com.purplemango.repository;

import com.purplemango.aop.operations.BeforeGlobalMongoOperation;
import com.purplemango.config.MultiTenantMongoDBFactory;
import com.purplemango.model.Tenant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@BeforeGlobalMongoOperation
public class TenantRepositoryImpl extends MongoBaseRepository<Tenant> implements TenantRepository {

    public static final String COLLECTION_NAME = "tenants";
    public static final String DATABASE_NAME = "gms-tenants";

    public TenantRepositoryImpl() {
        super.setDatabaseName(DATABASE_NAME);
    }
    @Override
    public Tenant findByCompanyNameAndCompanyCode(String companyName, String companyCode) {
        return null;
    }

    @Override
    public Collection<Tenant> findAll() {
        return List.of();
    }

    @Override
    public Page<Tenant> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Tenant findById(String tenantId) {
        Query query = new Query(Criteria.where("id").is(tenantId));
        return findOneByQuery(query, Tenant.class, COLLECTION_NAME);
    }

    @Override
    public Tenant save(Tenant tenant) {
       return save(tenant, COLLECTION_NAME);
    }
}
