package com.banksystem.notification.service;

import com.banksystem.notification.entity.Notification;
import com.banksystem.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Notification create(Notification notification) {
        notification.setStatus("PENDING");
        return repository.save(notification);
    }

    @Transactional(readOnly = true)
    public List<Notification> getAll() {
        return repository.findAllByOrderByCreatedAtDesc();
    }
}