package com.szs.restapi.globals.exception;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleMethodArgumentNotValid( MethodArgumentNotValidException exception ) {

        Map<String, Object> handleMethodArgumentNotValid = new HashMap<>(){{
            put("message", "요청 오류 :: " + exception.getMessage());
        }};

        JSONPObject errorResponseJson = new JSONPObject("JSON.parse", handleMethodArgumentNotValid);
        return new ResponseEntity<>(errorResponseJson, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<?> handleMethodTypeMismatchNotValid( MethodArgumentTypeMismatchException exception ) {

        Map<String, Object> handleMethodTypeMismatchNotValid = new HashMap<>(){{
            put("message", "요청 오류 :: " + exception.getMessage());
        }};

        JSONPObject errorResponseJson = new JSONPObject("JSON.parse", handleMethodTypeMismatchNotValid);
        return new ResponseEntity<>(errorResponseJson, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
