package ru.shortener.dto;

import ru.shortener.model.ShortLink;

public class ShortLinkResponse {
    String shortcode;
    public String getShortcode(){
        return shortcode;
    }
    public ShortLinkResponse(String shortcode){
        this.shortcode = shortcode;


    }
}
