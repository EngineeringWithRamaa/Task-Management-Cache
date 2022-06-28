package com.engineeringwithramaa.TaskCache.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public class Task implements Serializable {

    @Id
    private String id;

    private Long createdAt;

    private Boolean completed;

    private String description;

    public Task(String description) {
        this.id = UUID.randomUUID().toString().substring(0, 6);
        this.createdAt = Instant.now().toEpochMilli();
        this.description = description;
        this.completed = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}