package com.purplemango.repository;

import com.purplemango.config.MultiTenantMongoDBFactory;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Component
public class MongoBaseRepository<T>{

    private String databaseName;
    private String targetName;

    @Autowired private final MongoTemplate mongoTemplate;


    public MongoBaseRepository() {
        mongoTemplate = MultiTenantMongoDBFactory.getMongoTemplate();
    }

    public T save(T document, String collectionName) {
        return mongoTemplate.save(document, collectionName);
    }
    public T findById(Query query, Class<T> typeClass, String collectionName) {
        return mongoTemplate.findById(query, typeClass, collectionName);
    }
    public boolean existsById(Query query, Class<T> typeClass, String collectionName) {
        return mongoTemplate.exists(query, typeClass, collectionName);
    }
    public List<T> findAll(Class<T> typeClass, String collectionName) {
        return mongoTemplate.findAll(typeClass, collectionName);
    }

    public long count(Query query, Class<T> typeClass, String collectionName) {
        return mongoTemplate.count(query, typeClass, collectionName);
    }
    public void deleteById(Query query, Class<T> typeClass, String collectionName) {
        mongoTemplate.remove(query, typeClass, collectionName);
    }

    public T findOneByQuery(Query query, Class<T> typeClass, String collectionName) {
        return mongoTemplate.findOne(query, typeClass, collectionName);
    }

    @Data
    public static class QueryParameters {
        private Map<String, Object> criteria;
        private Map<String, Object> orCriteria;
        private Map<String, Object> lteCriteria;
        private Map<String, Object> gteCriteria;
        private int page;
        private int limit;
        private String sortField;
        private boolean ascending;
        private boolean regex;
        private Map<String, List<String>> inCriteria;

        public QueryParameters() {
            super();
            this.criteria = new HashMap<>();
            this.orCriteria = new HashMap<>();
            this.lteCriteria = new HashMap<>();
            this.gteCriteria = new HashMap<>();
            this.inCriteria = new HashMap<>();
        }
    }


}
