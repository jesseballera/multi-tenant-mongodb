package com.purplemango.controller;

import com.purplemango.model.AddTenant;
import com.purplemango.model.Tenant;
import com.purplemango.model.UpdateTenant;
import com.purplemango.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
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
    public ResponseEntity<?> getAllTenants(@RequestParam(required = true) int page,
                                           @RequestParam(required = true) int size,
                                           @RequestParam(required = true, value = "q") String  sort,
                                           @RequestParam(required = true) Sort.Direction direction) {
        if (page < 0 || size < 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tenantService.getAllTenants(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sort))));
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

    @PostMapping("/create-update")
    public ResponseEntity<?> createOrUpdateTenant(@RequestBody @Validated UpdateTenant entity) {
        Tenant tenant = tenantService.createOrUpdateTenant(entity);
        return ResponseEntity.ok(tenant);
    }
}
