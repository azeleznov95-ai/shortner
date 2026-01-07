package ru.shortener.util;


import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Encoder {
private static final String ALPHABET = "0123456789" +
        "abcdefghijklmnopqrstuvwxyz" +
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String encode (){
        String shortCode="";
        Random random = new Random();
        for(int length=0;length<=8;length++){
            int nextChar=random.nextInt(0,61);
            shortCode= shortCode+""+ALPHABET.charAt(nextChar);
        }
        return shortCode;
    }
}
