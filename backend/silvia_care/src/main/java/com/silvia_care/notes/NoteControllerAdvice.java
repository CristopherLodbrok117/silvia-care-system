package com.silvia_care.notes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NoteControllerAdvice {

    @ExceptionHandler(NoteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String noteNotFoundException(NoteException ex){
        return ex.getMessage();
    }
}
