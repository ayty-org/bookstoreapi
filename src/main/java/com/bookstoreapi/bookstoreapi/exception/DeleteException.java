package com.bookstoreapi.bookstoreapi.exception;

public class DeleteException extends RuntimeException{

    public DeleteException(Long id, String className){
        super(className+ " with id " +id+" cannot be deleted because it is in one or more purchases");
    }
}
