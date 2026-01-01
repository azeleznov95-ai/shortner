package ru.shortener.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class ShortLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String originalUrl;

    @Column(nullable = false, unique = true)
    private String shortcode;
    @Column(nullable = false, unique = false)
    private Long count;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalUrl() {
        return originalUrl;

    }

    public String getShortcode() {
        return shortcode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void incCount() {
        this.count++;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }
@PrePersist
    public void onCreated() {
        this.createdAt = LocalDateTime.now();
    }
}