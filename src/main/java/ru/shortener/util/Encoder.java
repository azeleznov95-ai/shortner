package ru.shortener.util;


import org.springframework.stereotype.Component;

@Component
public class Base62Encoder {
private static final String ALPHABET = "0123456789" +
        "abcdefghijklmnopqrstuvwxyz" +
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String encode (Long id){
        if (id==0){
            throw  new IllegalArgumentException("Id must be more than 0");
        }
        StringBuilder stringBuilder= new StringBuilder();
        for(;id>0;){
            stringBuilder.append(ALPHABET.charAt((int)(id%62)));
            id/=62;

        }
        return stringBuilder.reverse().toString();
    }
}
