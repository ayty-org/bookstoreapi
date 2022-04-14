package com.bookstoreapi.bookstoreapi.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundHandler(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgumentHandler(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidHandler(MethodArgumentNotValidException e){
        StringBuilder msg = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(
                error -> msg.append(error.getDefaultMessage()).append("\n")
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg.toString());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> illegalArgumentHandler(DeleteException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

}
