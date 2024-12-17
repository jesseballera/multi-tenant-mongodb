package com.purplemango.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Builder
@Document(collection = "tenants")
public record Tenant(@MongoId String id,
                     @Indexed(unique = true) String companyName,
                     @Indexed(unique = true) String companyCode) {

    public static Tenant build(AddTenant addTenant) {
        return Tenant.builder()
                .id(UUID.randomUUID().toString())
                .companyName(addTenant.companyName())
                .companyCode(addTenant.companyCode())
                .build();
    }

//    public static class Builder {
//        public Tenant build(AddTenant addTenant) {
//            return Tenant.builder()
//                    .id(ObjectId.get())
//                    .companyName(addTenant.companyName())
//                    .companyCode(addTenant.companyCode())
//                    .build();
//        }
//    }
}
