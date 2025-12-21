package ru.shortener.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class AppProperties {
    @Value("${app.base-url}")
    private String Baseurl;
    public String getBaseurl(){
        return Baseurl;
    }

}
