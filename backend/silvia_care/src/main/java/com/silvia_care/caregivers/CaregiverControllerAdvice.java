package com.silvia_care.caregivers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CaregiverControllerAdvice {

    @ExceptionHandler(CaregiverNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(CaregiverNotFoundException ex){
        return ex.getMessage();
    }
}
