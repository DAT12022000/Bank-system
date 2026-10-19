package com.banksystem.audit.controller;

import com.banksystem.audit.entity.AuditLog;
import com.banksystem.audit.service.AuditService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditController {

    private final AuditService service;

    public AuditController(AuditService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuditLog create(@RequestBody AuditLog log) {
        return service.create(log);
    }

    @GetMapping
    public List<AuditLog> getAll() {
        return service.getAll();
    }
}