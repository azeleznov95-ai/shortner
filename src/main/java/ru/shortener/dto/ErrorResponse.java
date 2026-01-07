package ru.shortener.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
    int status;
    String message;
    LocalDateTime brokeAt;
    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setBrokeAt(LocalDateTime brokeAt) {
        this.brokeAt = brokeAt;
    }

    public LocalDateTime getBrokeAt() {
        return brokeAt;
    }
}
