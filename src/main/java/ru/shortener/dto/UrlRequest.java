package ru.shortener.dto;

import jakarta.validation.constraints.NotBlank;


import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

public class UrlRequest {
    @NotBlank(message = "URL must not be empty")
    @Pattern(
            regexp = "^(https|http)://.+$",
            message = "URL must start with http:// or https://"
    )
    private String url;
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return url;
    }



}
