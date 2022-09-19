/**
 * Created By shoh@n
 * Date: 9/19/2022
 */

package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class GiftCardNotFoundException extends ApiException {

    public GiftCardNotFoundException(String id) {
        super("Cannot found giftCard with id " + id, HttpStatus.NOT_FOUND);
    }
}
