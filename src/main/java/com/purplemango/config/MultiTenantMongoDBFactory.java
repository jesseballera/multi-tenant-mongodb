package com.purplemango.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MultiTenantMongoDBFactory extends SimpleMongoClientDatabaseFactory {
    public final String DEFAULT_DB_INSTANCE;
    static final ThreadLocal<String> dbName = new ThreadLocal<>();
    private static MongoTemplate mongoTemplate;
    private static final Map<String, Object> databaseIndexMap = new HashMap<String, Object>();

    public MultiTenantMongoDBFactory(final MongoClient mongoClient, final String defaultDatabaseName) {
        super(mongoClient, defaultDatabaseName);
        this.DEFAULT_DB_INSTANCE = defaultDatabaseName;
    }

    @Bean
    public static MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(final MongoTemplate mongoTemplate) {
        Assert.isNull(this.mongoTemplate, "Mongo Template already set");
        this.mongoTemplate = mongoTemplate;
    }

    public static void setDatabaseNameForCurrentThread(final String databaseName) {
        log.info("Setting database name to {}", databaseName);
        dbName.set(databaseName);
    }

    @Override
    public MongoDatabase getMongoDatabase() throws DataAccessException {
        MongoDatabase database = null;
        final String tLName = dbName.get();
        final String finalDBToUse = tLName != null ? tLName : DEFAULT_DB_INSTANCE;
        log.info("Database name is {}", finalDBToUse);
        return super.getMongoDatabase(finalDBToUse);
    }


    @Override
    public void destroy() throws Exception {
        dbName.remove();
    }
}
