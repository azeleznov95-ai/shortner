package ru.shortener.exceptons;

public class ShortLinkNotFoundException extends  RuntimeException{
    public  ShortLinkNotFoundException(String message){
        super(message);
    }
}
