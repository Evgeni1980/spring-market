package ru.kremenia.market.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String massage) {
        super(massage);
    }
}
