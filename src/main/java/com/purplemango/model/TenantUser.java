package com.purplemango.model;

import lombok.Builder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Builder
@Document(collection = "users")
public record TenantUser(
        @MongoId String id,
        String tenant,
        String firstName,
        String lastName) {

    public static TenantUser build(AddTenantUser addTenantUser, String tenant) {
        return TenantUser.builder()
                .id(UUID.randomUUID().toString())
                .tenant(tenant)
                .firstName(addTenantUser.firstName())
                .lastName(addTenantUser.lastName())
                .build();
    }
}
