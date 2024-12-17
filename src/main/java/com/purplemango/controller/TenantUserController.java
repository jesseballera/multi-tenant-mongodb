package com.purplemango.controller;

import com.purplemango.model.AddTenant;
import com.purplemango.model.AddTenantUser;
import com.purplemango.model.Tenant;
import com.purplemango.model.TenantUser;
import com.purplemango.service.TenantService;
import com.purplemango.service.TenantUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/v1/tenant-users")
public class TenantUserController {

    TenantUserService tenantUserService;

    @Autowired
    public TenantUserController(TenantUserService tenantUserService) {
        this.tenantUserService = tenantUserService;
    }

//    @GetMapping
//    public  ResponseEntity<?> getAllTenants() { return ResponseEntity.ok(tenantService.getAllTenants()); }
//
//    @GetMapping("/page")
//    public ResponseEntity<?> getAllTenants(@RequestParam int page, @RequestParam int size) {
//        return ResponseEntity.ok(tenantService.getAllTenants(PageRequest.of(page, size)));
//    }
//
//    @GetMapping("/{tenantId}")
//    public ResponseEntity<?> getTenantById(@PathVariable UUID tenantId) {
//        return ResponseEntity.ok(tenantService.getTenantById(tenantId));
//    }

    @PostMapping("/{tenant-id}")
    public ResponseEntity<?> createTenant(@PathVariable("tenant-id") String tenantId, @RequestBody @Validated AddTenantUser entity) {
        if (tenantId == null || tenantId.isEmpty() || !tenantId.equals(entity.tenantId())) {
            return ResponseEntity.badRequest().build();
        }
        TenantUser tenantUser = tenantUserService.createTenantUser(entity);
        return ResponseEntity.ok(tenantUser);
    }
}
