package com.purplemango.model;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

public record AddTenantUser(
        String tenantId,
        String firstName,
        String lastName) { }
