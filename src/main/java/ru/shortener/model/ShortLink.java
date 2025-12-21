package ru.shortener.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class ShortLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    String originalUrl;
    @Column(unique = true)
    String shortcode;
    LocalDateTime createdAt =LocalDateTime.now();
    Long count=0L;
    public Long getCount() {
        return count;
    }
    public void setCount(Long count) {
        this.count = count;
    }
    public Long getId() {
        return Id;
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
    public void incCount(){
        this.count++;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
