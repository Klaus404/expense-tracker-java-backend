package com.example.klaus404.expensemanager.exception;

import com.example.klaus404.expensemanager.dto.payload.exception.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException ex){
        return ErrorResponse.builder()
                .errCode(HttpStatus.NO_CONTENT.value())
                .errMsg(ex.getMessage())
                .build();
    }
}
