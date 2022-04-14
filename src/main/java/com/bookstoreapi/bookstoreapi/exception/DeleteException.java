package com.bookstoreapi.bookstoreapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DeleteException extends RuntimeException{

    public DeleteException(Long id, String className){
        super(className+ " with id " +id+" cannot be deleted because it is in one or more purchases");
    }
}
