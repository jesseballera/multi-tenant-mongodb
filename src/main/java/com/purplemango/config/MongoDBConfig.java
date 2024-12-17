package com.purplemango.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
@RequiredArgsConstructor
public class MongoDBConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    String mongodbUri;

    @Value("${spring.data.mongodb.database}")
    String mongodbDatabase;

    @Override
    protected String getDatabaseName() {
        ThreadLocal<String> threadLocal = MultiTenantMongoDBFactory.dbName;
        String dbName = threadLocal.get();
        if (dbName == null || dbName.isEmpty()) {
            dbName = mongodbDatabase;
        }
        return dbName;
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongodbUri);
    }

    @Primary
    @Bean("multiTenantMongoDBFactory")
    public MultiTenantMongoDBFactory multiTenantMongoDBFactory(final MongoClient mongoClient) {
        return new MultiTenantMongoDBFactory(mongoClient, getDatabaseName());
    }
}
