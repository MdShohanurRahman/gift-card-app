/**
 * Created By shoh@n
 * Date: 8/13/2022
 */

package com.example.demo.exceptions;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.queryhandling.QueryExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    ResponseEntity<ResponseError> handleApiException(ApiException ex) {
        ex.printStackTrace();
        ResponseError error = new ResponseError(ex.getHttpStatus(), ex);
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    @ExceptionHandler(CommandExecutionException.class)
    ResponseEntity<ResponseError> handleApiException(CommandExecutionException ex) {
        ex.printStackTrace();
        ResponseError error = new ResponseError(HttpStatus.BAD_REQUEST, ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QueryExecutionException.class)
    ResponseEntity<ResponseError> handleApiException(QueryExecutionException ex) {
        ex.printStackTrace();
        ResponseError error = new ResponseError(HttpStatus.BAD_REQUEST, ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
