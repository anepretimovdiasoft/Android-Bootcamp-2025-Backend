package com.example.bootcamp.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "is_admin_message", nullable = false)
    private Boolean isAdminMessage;

    @Column(name = "is_notification", nullable = false)
    private Boolean isNotification;

    @Column(name = "status", nullable = false)
    private String status;  // 'sent', 'failed', 'canceled', 'read'

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIsAdminMessage() {
        return isAdminMessage;
    }

    public void setIsAdminMessage(Boolean isAdminMessage) {
        this.isAdminMessage = isAdminMessage;
    }

    public Boolean getIsNotification() {
        return isNotification;
    }

    public void setIsNotification(Boolean isNotification) {
        this.isNotification = isNotification;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
