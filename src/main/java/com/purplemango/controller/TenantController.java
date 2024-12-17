package com.purplemango.controller;

import com.purplemango.model.AddTenant;
import com.purplemango.model.Tenant;
import com.purplemango.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/v1/tenants")
public class TenantController {

    TenantService tenantService;

    @Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping("/all")
    public  ResponseEntity<?> getAllTenants() { return ResponseEntity.ok(tenantService.getAllTenants()); }

    @GetMapping
    public ResponseEntity<?> getAllTenants(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(tenantService.getAllTenants(PageRequest.of(page, size)));
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> getTenantById(@RequestParam("tenant-id") String tenantId) {
        return ResponseEntity.ok(tenantService.getTenantById(tenantId));
    }

    @PostMapping
    public ResponseEntity<?> createTenant(@RequestBody @Validated AddTenant entity) {
        Tenant tenant = tenantService.createTenant(entity);
        return ResponseEntity.ok(tenant);
    }
}
