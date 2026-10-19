package com.banksystem.audit.service;

import com.banksystem.audit.entity.AuditLog;
import com.banksystem.audit.repository.AuditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AuditService {

    private final AuditRepository repository;

    public AuditService(AuditRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AuditLog create(AuditLog log) {
        return repository.save(log);
    }

    @Transactional(readOnly = true)
    public List<AuditLog> getAll() {
        return repository.findAllByOrderByCreatedAtDesc();
    }
}