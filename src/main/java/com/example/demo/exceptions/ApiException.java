/**
 * Created By shoh@n
 * Date: 9/19/2022
 */

package com.example.demo.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ApiException extends RuntimeException {

    private HttpStatus httpStatus;

    public ApiException() {
        this("Your request could not be processed");
    }

    public ApiException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
