package com.notes.app.Scribler.Entity;

import jakarta.persistence.*;
import org.aspectj.weaver.loadtime.definition.Definition;

import java.time.Instant;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String title;
    @Column(columnDefinition = "TEXT")
    private  String content;

    @Column(nullable = false)
    private java.time.Instant createdAt;
    @Column(nullable = false)
    private java.time.Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Long createdBy;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    public Instant getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Long getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
    public Tenant getTenant() {
        return tenant;
    }
    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
