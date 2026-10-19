package com.banksystem.notification.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String recipient;

    @Column(nullable = false, length = 30)
    private String channel;

    @Column(length = 255)
    private String subject;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false, length = 30)
    private String status = "PENDING";

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (status == null) {
            status = "PENDING";
        }
    }

    public Long getId() { return id; }

    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getChannel() { return channel; }
    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSubject() { return subject; }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
}