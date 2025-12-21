package ru.shortener.dto;

import java.time.LocalDateTime;

public class ShortLinkStatsResponse {


    String originalUrl;
    String shortcode;
    LocalDateTime createdAt;
    Long count=0L;

    public String getOriginalUrl() {
        return originalUrl;
    }
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
    public String getShortcode() {
        return shortcode;
    }
    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public Long getCount() {
        return count;
    }
    public void setCount(Long count) {
        this.count = count;
    }

}
