/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.transaction.controller;

import dto.ErrorDTO;
import exception.TransactionNotValidException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 *
 * @author edgar
 */
@ControllerAdvice
public class RestControllerAdvice {
    
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDTO> runTimeExceptionHandler(RuntimeException ex){
        ErrorDTO error = ErrorDTO.builder().code(ex.getMessage()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = TransactionNotValidException.class)
    public ResponseEntity<ErrorDTO> TransactionNotValidExceptionHandler(TransactionNotValidException ex){
        ErrorDTO error = ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
   }
}
