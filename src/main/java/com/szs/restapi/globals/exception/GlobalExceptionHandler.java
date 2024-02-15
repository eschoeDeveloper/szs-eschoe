package com.szs.restapi.globals.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<?> runtimeException( RuntimeException exception ) {

        Map<String, Object> handleMethodArgumentNotValid = new HashMap<>(){{
            put("message", "요청 오류 :: " + exception.getMessage());
        }};

        JSONPObject errorResponseJson = new JSONPObject("JSON.parse", handleMethodArgumentNotValid);
        return new ResponseEntity<>(errorResponseJson.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * API 호출 시 '객체' 혹은 '파라미터' 데이터 값이 유효하지 않은 경우
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleMethodArgumentNotValid( MethodArgumentNotValidException exception ) {

        Map<String, Object> handleMethodArgumentNotValid = new HashMap<>(){{
            put("message", "요청 오류 :: " + exception.getMessage());
        }};

        JSONPObject errorResponseJson = new JSONPObject("JSON.parse", handleMethodArgumentNotValid);
        return new ResponseEntity<>(errorResponseJson.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * API 호출 시 'Header' 내에 데이터 값이 유효하지 않은 경우
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<?> handleMethodTypeMismatchNotValid( MethodArgumentTypeMismatchException exception ) {

        Map<String, Object> handleMethodTypeMismatchNotValid = new HashMap<>(){{
            put("message", "요청 오류 :: " + exception.getMessage());
        }};

        JSONPObject errorResponseJson = new JSONPObject("JSON.parse", handleMethodTypeMismatchNotValid);
        return new ResponseEntity<>(errorResponseJson.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * API 호출 시 'Header' 내에 데이터 값이 유효하지 않은 경우
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    protected ResponseEntity<?> missingRequestHeader(MissingRequestHeaderException exception) {

        Map<String, Object> missingRequestHeader = new HashMap<>(){{
            put("message", "요청 오류 :: " + exception.getMessage());
        }};

        JSONPObject errorResponseJson = new JSONPObject("JSON.parse", missingRequestHeader);
        return new ResponseEntity<>(errorResponseJson.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * 클라이언트에서 Body로 '객체' 데이터가 넘어오지 않았을 경우
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<?> httpMessageNotReadable(HttpMessageNotReadableException exception) {

        Map<String, Object> httpMessageNotReadable = new HashMap<>(){{
            put("message", "요청 오류 :: " + exception.getMessage());
        }};

        JSONPObject errorResponseJson = new JSONPObject("JSON.parse", httpMessageNotReadable);
        return new ResponseEntity<>(errorResponseJson.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * 클라이언트에서 request로 '파라미터로' 데이터가 넘어오지 않았을 경우
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<?> missingServletRequestParameter(MissingServletRequestParameterException exception) {

        Map<String, Object> missingServletRequestParameter = new HashMap<>(){{
            put("message", "요청 오류 :: " + exception.getMessage());
        }};

        JSONPObject errorResponseJson = new JSONPObject("JSON.parse", missingServletRequestParameter);
        return new ResponseEntity<>(errorResponseJson.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * 잘못된 서버 요청일 경우 발생한 경우
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    protected ResponseEntity<?> badRequest(HttpClientErrorException exception) {

        Map<String, Object> badRequest = new HashMap<>(){{
            put("message", "요청 오류 :: " + exception.getMessage());
        }};

        JSONPObject errorResponseJson = new JSONPObject("JSON.parse", badRequest);
        return new ResponseEntity<>(errorResponseJson.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * 잘못된 주소로 요청 한 경우
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<?> noHandlerFound(NoHandlerFoundException exception) {

        Map<String, Object> noHandlerFound = new HashMap<>(){{
            put("message", "요청 오류 :: " + exception.getMessage());
        }};

        JSONPObject errorResponseJson = new JSONPObject("JSON.parse", noHandlerFound);
        return new ResponseEntity<>(errorResponseJson.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * NULL 값이 발생한 경우
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<?> nullPointerException(NullPointerException exception) {

        Map<String, Object> nullPointerException = new HashMap<>(){{
            put("message", "요청 오류 :: " + exception.getMessage());
        }};

        JSONPObject errorResponseJson = new JSONPObject("JSON.parse", nullPointerException);
        return new ResponseEntity<>(errorResponseJson.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * Input / Output 내에서 발생한 경우
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(IOException.class)
    protected ResponseEntity<?> ioException(IOException exception) {

        Map<String, Object> ioException = new HashMap<>(){{
            put("message", "요청 오류 :: " + exception.getMessage());
        }};

        JSONPObject errorResponseJson = new JSONPObject("JSON.parse", ioException);
        return new ResponseEntity<>(errorResponseJson.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * com.fasterxml.jackson.core 내에 Exception 발생하는 경우
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(JsonProcessingException.class)
    protected ResponseEntity<?> jsonProcessingException(JsonProcessingException exception) {

        Map<String, Object> jsonProcessingException = new HashMap<>(){{
            put("message", "요청 오류 :: " + exception.getMessage());
        }};

        JSONPObject errorResponseJson = new JSONPObject("JSON.parse", jsonProcessingException);
        return new ResponseEntity<>(errorResponseJson.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
