package com.example.demo.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseError {

    private HttpStatus status;
    private String message;

    ResponseError(HttpStatus status, Throwable ex) {
        this.status = status;
        this.message = ex.getMessage();
    }
}
