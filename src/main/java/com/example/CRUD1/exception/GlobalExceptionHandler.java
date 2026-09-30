package com.example.CRUD1.exception;

import org.springframework.beans.factory.support.ManagedProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> gererErreursValidation(MethodArgumentNotValidException exception){
        Map<String,String> erreurs=new HashMap<>();

        for (FieldError error:  exception.getBindingResult().getFieldErrors()){
            erreurs.put(error.getField(),error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(erreurs);
    }
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<?> gererErreurConversion(){
//        return ResponseEntity.badRequest().body("la structure ou le format des données envoyées est invalide");
//    }

}
