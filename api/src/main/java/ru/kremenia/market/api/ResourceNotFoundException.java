package ru.kremenia.market.api;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String massage) {
        super(massage);
    }

}
