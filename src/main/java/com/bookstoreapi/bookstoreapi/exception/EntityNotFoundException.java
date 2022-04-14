package com.bookstoreapi.bookstoreapi.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(Long id, String className){
        super(className+" with id "+id+" not found");
    }
}
