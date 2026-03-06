package com.substring.irctc.exceptions;


import com.substring.irctc.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchException(NoSuchElementException exception){
        ErrorResponse response=new ErrorResponse("Train not found!! "+ exception.getMessage(),"404",false);
        ResponseEntity<ErrorResponse> responseResponseEntity=new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return responseResponseEntity;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchException(ResourceNotFoundException exception){
        ErrorResponse response=new ErrorResponse( exception.getMessage(),"404",false);
        ResponseEntity<ErrorResponse> responseResponseEntity=new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return responseResponseEntity;
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Map<String ,String>> handleTransactionSystemException(TransactionSystemException transactionSystemException){
        Map<String ,String > errorResponse=new HashMap<>();
        Throwable cause = transactionSystemException.getRootCause();

        if(cause instanceof ConstraintViolationException violationException){
            violationException.getConstraintViolations().forEach(v->{
                String field=v.getPropertyPath().toString();
                String message=v.getMessage();
                errorResponse.put(field,message);
            });
        }
        ResponseEntity<Map<String ,String >> error=new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
        return error;
    }
}
