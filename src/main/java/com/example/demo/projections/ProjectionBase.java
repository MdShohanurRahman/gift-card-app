/**
 * Created By shoh@n
 * Date: 9/19/2022
 */

package com.example.demo.projections;

import org.axonframework.messaging.interceptors.ExceptionHandler;

public class ProjectionBase {

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }

}
