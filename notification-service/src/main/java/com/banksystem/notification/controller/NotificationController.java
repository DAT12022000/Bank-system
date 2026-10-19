package com.banksystem.notification.controller;

import com.banksystem.notification.entity.Notification;
import com.banksystem.notification.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Notification create(@RequestBody Notification notification) {
        return service.create(notification);
    }

    @GetMapping
    public List<Notification> getAll() {
        return service.getAll();
    }
}